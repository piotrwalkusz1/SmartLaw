package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract

import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common.SmartContractBuilder
import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.solidity.SoliditySmartContractBuilder
import com.piotrwalkusz.smartlaw.core.model.document.Contract

class FromContractToSmartContractConverter {

    fun convert(contract: Contract): String {
        val builder: SmartContractBuilder = SoliditySmartContractBuilder()
        builder.name = contract.name

        return builder.build()
    }
}