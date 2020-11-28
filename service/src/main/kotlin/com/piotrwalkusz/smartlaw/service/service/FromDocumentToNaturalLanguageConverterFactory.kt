package com.piotrwalkusz.smartlaw.service.service

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.FromDocumentToNaturalLanguageConverter
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import org.springframework.stereotype.Service

@Service
class FromDocumentToNaturalLanguageConverterFactory(private val templateProcessorService: TemplateProcessorService) {

    fun create(ruleProvider: RuleProvider): FromDocumentToNaturalLanguageConverter {
        return FromDocumentToNaturalLanguageConverter(ruleProvider, templateProcessorService)
    }
}