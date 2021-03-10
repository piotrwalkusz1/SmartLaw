package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.solidity

import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common.SmartContractBuilder
import com.piotrwalkusz.smartlaw.compiler.element.BasicTypes
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.element.state.State

class SoliditySmartContractBuilder : SmartContractBuilder() {

    companion object {
        val BASIC_TYPES_MAP = mapOf(
                BasicTypes.UINT.id to "uint",
                BasicTypes.ADDRESS.id to "address",
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
                .forEach { appendVariable(convertType(it.type), it.name, setOf(PUBLIC)) }
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

    private fun convertType(type: Type): String {
        if (type !is DefinitionRef) {
            throw IllegalArgumentException("Expected instance of DefinitionRef class. ${type.javaClass} class was.")
        }

        return BASIC_TYPES_MAP[type.definition]
                ?: throw IllegalArgumentException("Id ${type.definition} is not supported")
    }
}