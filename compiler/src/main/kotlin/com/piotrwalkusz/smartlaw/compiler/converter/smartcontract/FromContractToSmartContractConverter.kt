package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract

import com.piotrwalkusz.smartlaw.compiler.converter.elements.FromContractToElementsConverter
import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common.SmartContractBuilder
import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.solidity.SoliditySmartContractBuilder
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.rule.RuleUtils
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleElementsTemplateProcessor
import com.piotrwalkusz.smartlaw.compiler.validator.ValidatorService
import com.piotrwalkusz.smartlaw.core.model.document.Contract
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.SectionPresentationElement

class FromContractToSmartContractConverter(
        private val fromContractToElementsConverter: FromContractToElementsConverter
) {

    fun convert(contract: Contract): String {
        val builder: SmartContractBuilder = SoliditySmartContractBuilder()
        builder.name = contract.name
        builder.elements = fromContractToElementsConverter.getElements(contract)

        return builder.build()
    }
}