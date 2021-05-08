package com.piotrwalkusz.smartlaw.service.service

import com.piotrwalkusz.smartlaw.compiler.converter.logic.FromContractToLogicRulesConverter
import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.FromContractToSmartContractConverter
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.validator.ElementValidatorService
import org.springframework.stereotype.Service

@Service
class FromContractToLogicRulesConverterFactory(
        private val fromContractToElementsConverterFactory: FromContractToElementsConverterFactory,
        private val elementValidatorService: ElementValidatorService
) {

    fun create(ruleProvider: RuleProvider): FromContractToLogicRulesConverter {
        val fromContractToElementsConverter = fromContractToElementsConverterFactory.create(ruleProvider)

        return FromContractToLogicRulesConverter(fromContractToElementsConverter, elementValidatorService)
    }
}