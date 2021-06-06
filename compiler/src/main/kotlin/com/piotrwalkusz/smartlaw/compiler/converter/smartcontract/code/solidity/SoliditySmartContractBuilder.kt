package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.solidity

import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common.SmartContractBuilder
import com.piotrwalkusz.smartlaw.compiler.element.BasicType
import com.piotrwalkusz.smartlaw.compiler.validator.model.*
import net.pearx.kasechange.toCamelCase
import net.pearx.kasechange.toPascalCase
import org.apache.commons.lang3.StringUtils

class SoliditySmartContractBuilder : SmartContractBuilder() {

    companion object {
        val BASIC_TYPES_MAP = mapOf(
                BasicType.UINT.id to "uint",
                BasicType.ADDRESS.id to "address",
                BasicType.STRING.id to "string"
        )
        const val INTEND = "  "

        const val PUBLIC = "public"
        const val PRIVATE = "private"
        const val PAYABLE = "payable"
    }

    override var name: String? = null
    override var elements: List<ValidatedElement> = listOf()
    private var sourceCode = StringBuilder()
    private var currentIntend: Int = 0

    override fun build(): String {
        val contractName = name ?: throw IllegalStateException("Cannot build smart contract without name")

        sourceCode
                .clear()
                .appendLine("// SPDX-License-Identifier: GPL-3.0")
                .appendLine("pragma solidity ^0.8.1;")
        beginContract(getContractName(contractName))
        elements
                .filterIsInstance<ValidatedDefinition>()
                .forEach { appendStruct(it) }
        elements
                .filterIsInstance<ValidatedState>()
                .forEach { appendVariable(it) }
        elements
                .filterIsInstance<ValidatedEnumDefinition>()
                .forEach { appendEnum(getEnumName(it.name), it.variants.map { variant -> getEnumVariantName(variant) }) }
        elements
                .filterIsInstance<ValidatedFunction>()
                .forEach { appendFunction(it.name, it.body, setOf(PRIVATE)) }
        elements
                .filterIsInstance<ValidatedActionDefinition>()
                .forEach { appendAction(it) }
        endContract()

        return sourceCode.toString()
    }

    private fun beginContract(contractName: String) {
        sourceCode
                .append("contract ")
                .append(contractName)
                .append(" {")
        beginIntend()
    }

    private fun endContract() {
        endIntend()
        sourceCode.append("}")
        newLine()
    }

    private fun appendEnum(name: String, variants: List<String>) {
        beginEnum(name)
        appendEnumVariants(variants)
        endEnum()
    }

    private fun beginEnum(name: String) {
        sourceCode
                .append("enum ")
                .append(name)
                .append(" {")
        beginIntend()
    }

    private fun endEnum() {
        endIntend()
        sourceCode.append("}")
        newLine()
    }

    private fun appendEnumVariants(variants: List<String>) {
        sourceCode.append(variants.joinToString(", "))
        newLine()
    }

    private fun appendAction(actionDefinition: ValidatedActionDefinition) {
        val callFunction = ValidatedFunctionCall(
                function = actionDefinition.function,
                functionRef = actionDefinition.functionRef,
                arguments = listOf()
        )
        val validators = elements
                .filterIsInstance<ValidatedActionValidation>()
                .filter { it.action == actionDefinition.action }
        val body = listOf(callFunction)
        val modifiers = setOf(PUBLIC, PAYABLE)

        beginFunction(getActionName(actionDefinition.name), modifiers)
        validators.forEach {
            sourceCode.append("require(")
            appendStatement(it.condition)
            sourceCode.append(");")
            newLine()
        }
        appendFunctionBody(body)
        endFunction()
    }

    private fun appendFunction(name: String, body: List<ValidatedStatement>, modifiers: Set<String> = setOf()) {
        beginFunction(getFunctionName(name), modifiers)
        appendFunctionBody(body)
        endFunction()
    }

    private fun beginFunction(name: String, modifiers: Set<String> = setOf()) {
        sourceCode
                .append("function ")
                .append(name)
                .append("() ")
                .append(modifiers.joinToString(separator = "") { "$it " })
                .append("{")
        beginIntend()
    }

    private fun endFunction() {
        endIntend()
        sourceCode
                .append("}")
        newLine()
    }

    private fun appendFunctionBody(body: List<ValidatedStatement>) {
        body.forEach {
            appendStatement(it)
            sourceCode.append(";")
            newLine()
        }
    }

    private fun appendStatement(statement: ValidatedStatement) {
        when (statement) {
            is ValidatedAssignment -> {
                appendStatement(statement.variable)
                sourceCode.append(" = ")
                appendStatement(statement.value)
            }
            is ValidatedSimpleVariableRef -> {
                sourceCode.append(getVariableName(statement.name))
            }
            is ValidatedValue -> {
                sourceCode.append(getValueLiteral(statement))
            }
            is ValidatedBalanceOperation -> {
                sourceCode.append("address(this).balance")
            }
            is ValidatedTransferOperation -> {
                appendStatement(statement.destination)
                sourceCode.append(".transfer(")
                appendStatement(statement.count)
                sourceCode.append(")")
            }
            is ValidatedTransferValueOperation -> {
                sourceCode.append("msg.value")
            }
            is ValidatedEqualsOperation -> {
                appendStatement(statement.firstOperand)
                sourceCode.append(" == ")
                appendStatement(statement.secondOperand)
            }
            is ValidatedMathOperation -> {
                appendStatement(statement.firstOperand)
                sourceCode.append(" " + statement.getOperator() + " ")
                appendStatement(statement.secondOperand)
            }
            is ValidatedSenderOperation -> {
                sourceCode.append("msg.sender")
            }
            is ValidatedFunctionCall -> {
                sourceCode
                        .append(getFunctionName(statement.function.name))
                        .append("()")
            }
            is ValidatedConstantValue -> {
                sourceCode.append(statement.value.value)
            }
            else -> {
                throw IllegalArgumentException("${statement.javaClass} is not supported")
            }
        }
    }

    private fun appendStruct(definition: ValidatedDefinition) {
        sourceCode
                .append("struct ")
                .append(getStructName(definition.name))
                .append(" {")
        beginIntend()
        definition.properties.forEach {
            sourceCode
                    .append(getTypeName(it.type))
                    .append(" ")
                    .append(getPropertyName(it.name))
                    .append(";")
            newLine()
        }
        endIntend()
        sourceCode
                .append("}")
        newLine()
    }

    private fun appendVariable(state: ValidatedState) {
        val type = getTypeName(state.type)
        val name = getVariableName(state.name)
        val defaultValue = state.defaultValue?.let { defaultValue -> getValueLiteral(defaultValue) }
        val modifiers = mutableSetOf(PUBLIC)
        if (state.type is ValidatedBasicType && state.type.basicType == BasicType.ADDRESS) {
            modifiers.add(PAYABLE)
        }

        appendVariable(type, name, modifiers, defaultValue)
    }

    private fun appendVariable(type: String, name: String, modifiers: Set<String>, defaultValue: String? = null) {
        sourceCode
                .append(type)
                .append(" ")
        appendModifier(PAYABLE, modifiers)
        appendModifier(PUBLIC, modifiers)
        sourceCode
                .append(name)

        if (defaultValue != null) {
            sourceCode
                    .append(" = ")
                    .append(defaultValue)
        }

        sourceCode
                .append(";")
        newLine()
    }

    private fun beginIntend() {
        currentIntend++
        newLine()
    }

    private fun endIntend() {
        currentIntend--
        sourceCode.delete(sourceCode.length - INTEND.length, sourceCode.length)
    }

    private fun newLine() {
        sourceCode
                .appendLine()
                .append(INTEND.repeat(currentIntend))
    }

    private fun getTypeName(type: ValidatedType): String {
        return when (type) {
            is ValidatedBasicType -> {
                BASIC_TYPES_MAP[type.basicType.id]
                        ?: throw IllegalArgumentException("Basic type ${type.basicType.id} is not supported")
            }
            is ValidatedDefinitionRef -> {
                getDefinitionName(type.definition.name)
            }
            is ValidatedEnumDefinitionRef -> {
                getEnumName(type.enumDefinition.name)
            }
            else -> {
                throw IllegalArgumentException("${type.javaClass} is not supported")
            }
        }
    }

    private fun getDefinitionName(name: String): String {
        return StringUtils.stripAccents(name).toPascalCase()
    }

    private fun getEnumName(name: String): String {
        return StringUtils.stripAccents(name).toPascalCase()
    }

    private fun getStructName(name: String): String {
        return StringUtils.stripAccents(name).toPascalCase()
    }

    private fun getPropertyName(name: String): String {
        return StringUtils.stripAccents(name).toCamelCase()
    }

    private fun getVariableName(variableName: String): String {
        return StringUtils.stripAccents(variableName).toCamelCase()
    }

    private fun getEnumVariantName(enumVariant: String): String {
        return StringUtils.stripAccents(enumVariant).toPascalCase()
    }

    private fun getFunctionName(name: String): String {
        return "_" + StringUtils.stripAccents(name).toCamelCase()
    }

    private fun getActionName(name: String): String {
        return StringUtils.stripAccents(name).toCamelCase()
    }

    private fun getContractName(name: String): String {
        return StringUtils.stripAccents(name).toPascalCase()
    }

    private fun getValueLiteral(value: ValidatedValue): String {
        return when (value) {
            is ValidatedBasicTypeLiteralValue -> {
                if (value.basicValue.basicType == BasicType.ADDRESS) {
                    "payable(" + value.metaValue.value + ")"
                } else if (value.basicValue.basicType == BasicType.STRING) {
                    "\"" + value.metaValue.value + "\""
                } else {
                    value.metaValue.value
                }
            }
            is ValidatedEnumValue -> {
                getEnumName(value.enumDefinition.name) + "." + getEnumVariantName(value.value)
            }
            is ValidatedDefinitionValue -> {
                getStructName(value.definition.name) + "({" + value.values.map { getPropertyName(it.key) + " : " + getValueLiteral(it.value) }.joinToString(", ") + "})"
            }
            else -> {
                throw IllegalArgumentException("${value.javaClass} is not supported")
            }
        }
    }

    private fun appendModifier(modifier: String, modifiers: Set<String>) {
        if (modifiers.contains(modifier)) {
            sourceCode
                    .append(modifier)
                    .append(" ")
        }
    }
}