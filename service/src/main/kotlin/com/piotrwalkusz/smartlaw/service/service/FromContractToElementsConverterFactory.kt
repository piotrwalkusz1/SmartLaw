package com.piotrwalkusz.smartlaw.service.service

import com.piotrwalkusz.smartlaw.compiler.converter.elements.FromContractToElementsConverter
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleElementsTemplateProcessor
import org.springframework.stereotype.Service

@Service
class FromContractToElementsConverterFactory(
        private val ruleElementsTemplateProcessor: RuleElementsTemplateProcessor
) {

    fun create(ruleProvider: RuleProvider): FromContractToElementsConverter {
        return FromContractToElementsConverter(ruleProvider, ruleElementsTemplateProcessor)
    }
}