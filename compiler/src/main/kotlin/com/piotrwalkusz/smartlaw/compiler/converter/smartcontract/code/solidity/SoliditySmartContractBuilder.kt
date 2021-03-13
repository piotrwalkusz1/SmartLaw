package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.solidity

import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common.SmartContractBuilder
import com.piotrwalkusz.smartlaw.compiler.element.BasicTypes
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.element.definition.Definition
import com.piotrwalkusz.smartlaw.core.model.element.enumdefinition.EnumDefinition
import com.piotrwalkusz.smartlaw.core.model.element.state.State

class SoliditySmartContractBuilder : SmartContractBuilder() {

    companion object {
        val BASIC_TYPES_MAP = mapOf(
                BasicTypes.UINT.id to "uint",
                BasicTypes.ADDRESS.id to "address",
                BasicTypes.STRING.id to "string"
        )

        const val PUBLIC = "public"
        const val PAYABLE = "payable"
    }

    override var name: String? = null
    override var elements: List<Element> = listOf()

    private var sourceCode = StringBuilder()

    override fun build(): String {
        val contractName = name ?: throw IllegalStateException("Cannot build smart contract without name")

        sourceCode.clear()
        beginContract(contractName)
        elements
                .filterIsInstance<State>()
                .forEach { appendVariable(convertType(it.type, elements), it.name, setOf(PUBLIC)) }
        elements
                .filterIsInstance<EnumDefinition>()
                .forEach { appendEnum(it.name, it.variants.map { variant -> variant.name }) }
        endContract()

        return sourceCode.toString()
    }

    private fun beginContract(contractName: String) {
        sourceCode
                .append("contract ")
                .append(contractName)
                .append(" {")
                .appendLine()
    }

    private fun endContract() {
        sourceCode
                .append("}")
                .appendLine()
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
                .appendLine()
    }

    private fun endEnum() {
        sourceCode
                .append("}")
                .appendLine()
    }

    private fun appendEnumVariants(variants: List<String>) {
        sourceCode
                .append(variants.joinToString(", "))
                .appendLine()
    }

    private fun appendVariable(type: String, name: String, modifiers: Set<String>) {
        sourceCode
                .append(type)
                .append(" ")
                .append(modifiers.joinToString(" "))
                .append(" ")
                .append(name)
                .append(";")
                .appendLine()
    }

    private fun convertType(type: Type, elements: List<Element>): String {
        if (type !is DefinitionRef) {
            throw IllegalArgumentException("Expected instance of DefinitionRef class. ${type.javaClass} class was.")
        }

        val basicType = BASIC_TYPES_MAP[type.definition]
        if (basicType != null) {
            return basicType
        }

        val element = elements
                .find { it.id == type.definition }
                ?: throw IllegalArgumentException("Element with id ${type.definition} not found")

        if (element is Definition) {
            return element.name
        }
        if (element is EnumDefinition) {
            return element.name
        }

        throw IllegalArgumentException("Element with id ${type.definition} expected to be Definition or EnumDefinition")
    }
}