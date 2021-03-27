package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.solidity

import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common.SmartContractBuilder
import com.piotrwalkusz.smartlaw.compiler.element.BasicType
import com.piotrwalkusz.smartlaw.compiler.validator.model.*
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Statement
import net.pearx.kasechange.toCamelCase
import net.pearx.kasechange.toPascalCase

class SoliditySmartContractBuilder : SmartContractBuilder() {

    companion object {
        val BASIC_TYPES_MAP = mapOf(
                BasicType.UINT.id to "uint",
                BasicType.ADDRESS.id to "address",
                BasicType.STRING.id to "string"
        )
        val INTEND = "  "

        const val PUBLIC = "public"
        const val PAYABLE = "payable"
    }

    override var name: String? = null
    override var elements: List<ValidatedElement> = listOf()
    private var sourceCode = StringBuilder()
    private var currentIntend: Int = 0

    override fun build(): String {
        val contractName = name ?: throw IllegalStateException("Cannot build smart contract without name")

        sourceCode.clear()
        beginContract(contractName)
        elements
                .filterIsInstance<ValidatedState>()
                .forEach { appendVariable(getTypeName(it.type), getVariableName(it.name), setOf(PUBLIC), getValueLiteral(it.defaultValue)) }
        elements
                .filterIsInstance<ValidatedEnumDefinition>()
                .forEach { appendEnum(getEnumName(it.name), it.variants.map { variant -> getEnumVariantName(variant) }) }
        elements
                .filterIsInstance<ValidatedFunction>()
                .forEach { appendFunction(it.name, it.body) }
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

    private fun appendFunction(name: String, body: List<ValidatedStatement>) {
        beginFunction(name)
        appendFunctionBody(body)
        endFunction()
    }

    private fun beginFunction(name: String) {
        sourceCode
                .append("function ")
                .append(getFunctionName(name))
                .append("() {")
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
            is ValidatedEnumValue -> {
                sourceCode.append(getEnumVariantName(statement.value))
            }
            else -> {
                throw IllegalArgumentException("${statement.javaClass} is not supported")
            }
        }
    }

    private fun appendVariable(type: String, name: String, modifiers: Set<String>, defaultValue: String? = null) {
        sourceCode
                .append(type)
                .append(" ")
                .append(modifiers.joinToString(" "))
                .append(" ")
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
                BASIC_TYPES_MAP.get(type.basicType.id)
                        ?: throw IllegalArgumentException("Basic type ${type.basicType.id} is not supported")
            }
            is ValidatedDefinitionRef -> {
                type.definition.name.toPascalCase()
            }
            is ValidatedEnumDefinitionRef -> {
                getEnumName(type.enumDefinition.name)
            }
            else -> {
                throw IllegalArgumentException("${type.javaClass} is not supported")
            }
        }
    }

    private fun getEnumName(name: String): String {
        return name.toPascalCase()
    }

    private fun getVariableName(variableName: String): String {
        return variableName.toCamelCase()
    }

    private fun getEnumVariantName(enumVariant: String): String {
        return enumVariant.toPascalCase()
    }

    private fun getFunctionName(name: String): String {
        return name.toCamelCase()
    }

    private fun getValueLiteral(value: ValidatedValue?): String? {
        if (value == null) {
            return null
        }

        return when (value) {
            is ValidatedBasicTypeLiteralValue -> {
                value.metaValue.value
            }
            is ValidatedEnumValue -> {
                getEnumVariantName(value.value)
            }
            else -> {
                throw IllegalArgumentException("${value.javaClass} is not supported")
            }
        }
    }
}