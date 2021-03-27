package com.piotrwalkusz.smartlaw.service.service

import com.piotrwalkusz.smartlaw.compiler.converter.extend.PresentationElementExtender
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleContentTemplateProcessor
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleElementsTemplateProcessor
import com.piotrwalkusz.smartlaw.compiler.validator.ElementValidatorService
import com.piotrwalkusz.smartlaw.compiler.validator.RuleArgumentsValuesValidatorService
import org.springframework.stereotype.Service

@Service
class PresentationElementExtenderFactory(
        private val ruleContentTemplateProcessor: RuleContentTemplateProcessor,
        private val ruleElementsTemplateProcessor: RuleElementsTemplateProcessor,
        private val ruleArgumentsValuesValidatorService: RuleArgumentsValuesValidatorService,
        private val elementValidatorService: ElementValidatorService
) {

    fun create(ruleProvider: RuleProvider, config: PresentationElementExtender.Config): PresentationElementExtender {
        return PresentationElementExtender(
                ruleProvider,
                config,
                ruleArgumentsValuesValidatorService,
                ruleContentTemplateProcessor,
                ruleElementsTemplateProcessor,
                elementValidatorService
        )
    }
}