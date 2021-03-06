package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.solidity

import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common.SmartContractBuilder

class SoliditySmartContractBuilder : SmartContractBuilder() {

    override var name: String? = null

    override fun build(): String {
        val sourceCode: StringBuilder = StringBuilder()
        sourceCode
                .append("contract ")
                .append(name)
                .append(" {")

        sourceCode
                .append("}")

        return sourceCode.toString()
    }
}