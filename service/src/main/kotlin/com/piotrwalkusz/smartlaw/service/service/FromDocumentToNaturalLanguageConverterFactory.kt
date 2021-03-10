package com.piotrwalkusz.smartlaw.service.service

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.FromDocumentToNaturalLanguageConverter
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleContentTemplateProcessor
import com.piotrwalkusz.smartlaw.compiler.validator.ValidatorService
import org.springframework.stereotype.Service

@Service
class FromDocumentToNaturalLanguageConverterFactory(
        private val ruleContentTemplateProcessor: RuleContentTemplateProcessor,
        private val validatorService: ValidatorService
) {

    fun create(ruleProvider: RuleProvider, config: FromDocumentToNaturalLanguageConverter.Config): FromDocumentToNaturalLanguageConverter {
        return FromDocumentToNaturalLanguageConverter(ruleProvider, config, validatorService, ruleContentTemplateProcessor)
    }
}