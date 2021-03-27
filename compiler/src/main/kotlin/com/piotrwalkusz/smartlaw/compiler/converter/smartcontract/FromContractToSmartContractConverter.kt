package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract

import com.piotrwalkusz.smartlaw.compiler.converter.elements.FromContractToElementsConverter
import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common.SmartContractBuilder
import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.solidity.SoliditySmartContractBuilder
import com.piotrwalkusz.smartlaw.compiler.validator.ElementValidatorService
import com.piotrwalkusz.smartlaw.core.model.document.Contract

class FromContractToSmartContractConverter(
        private val fromContractToElementsConverter: FromContractToElementsConverter,
        private val elementValidatorService: ElementValidatorService
) {

    fun convert(contract: Contract): String {
        val elements = fromContractToElementsConverter.getElements(contract)
        val validatedElements = elementValidatorService.validateElements(elements)

        val builder: SmartContractBuilder = SoliditySmartContractBuilder()
        builder.name = contract.name
        builder.elements = validatedElements

        return builder.build()
    }
}