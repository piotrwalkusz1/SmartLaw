package com.piotrwalkusz.smartlaw.service.service

import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.FromContractToSmartContractConverter
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleElementsTemplateProcessor
import com.piotrwalkusz.smartlaw.compiler.validator.ValidatorService
import org.springframework.stereotype.Service

@Service
class FromContractToSmartContractConverterFactory(
        private val fromContractToElementsConverterFactory: FromContractToElementsConverterFactory
) {

    fun create(ruleProvider: RuleProvider): FromContractToSmartContractConverter {
        val fromContractToElementsConverter = fromContractToElementsConverterFactory.create(ruleProvider)

        return FromContractToSmartContractConverter(fromContractToElementsConverter)
    }
}