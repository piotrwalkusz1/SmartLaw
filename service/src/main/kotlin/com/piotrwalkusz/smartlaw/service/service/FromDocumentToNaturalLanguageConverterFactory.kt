package com.piotrwalkusz.smartlaw.service.service

import com.piotrwalkusz.smartlaw.compiler.converter.extend.PresentationElementExtender
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.FromDocumentToNaturalLanguageConverter
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleContentTemplateProcessor
import com.piotrwalkusz.smartlaw.compiler.validator.RuleArgumentsValuesValidatorService
import org.springframework.stereotype.Service

@Service
class FromDocumentToNaturalLanguageConverterFactory(
        private val presentationElementExtenderFactory: PresentationElementExtenderFactory
) {

    fun create(ruleProvider: RuleProvider, config: PresentationElementExtender.Config): FromDocumentToNaturalLanguageConverter {
        val presentationElementExtender = presentationElementExtenderFactory.create(ruleProvider, config)

        return FromDocumentToNaturalLanguageConverter(presentationElementExtender)
    }
}