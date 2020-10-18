package com.piotrwalkusz.smartlaw.converter

import com.piotrwalkusz.smartlaw.model.document.ConvertibleToNaturalLanguage
import com.piotrwalkusz.smartlaw.model.rule.textformatter.RuleInvocationTextFormatter
import com.piotrwalkusz.smartlaw.model.rule.textformatter.SimpleRuleInvocationTextFormatter
import com.piotrwalkusz.smartlaw.model.template.Template
import com.piotrwalkusz.smartlaw.provider.RuleProvider
import com.piotrwalkusz.smartlaw.template.TemplateProcessor
import com.piotrwalkusz.smartlaw.template.TemplateProcessorContext

class FromDocumentToNaturalLanguageConverter(
        private val ruleProvider: RuleProvider,
        private val templateProcessors: List<TemplateProcessor<Template<String>, String>>
) {

    fun convert(document: ConvertibleToNaturalLanguage): String {
        return document.ruleInvocationTextFormatters.joinToString("\n") {
            convertRuleInvocationToNaturalLanguage(it)
        }
    }

    private fun convertRuleInvocationToNaturalLanguage(ruleInvocationTextFormatter: RuleInvocationTextFormatter): String {
        if (ruleInvocationTextFormatter is SimpleRuleInvocationTextFormatter) {
            val rule = ruleProvider.getRule(ruleInvocationTextFormatter.ruleInvocation.ruleId)!!
            val templateProcessor = getTemplateProcessor(rule.content)
            val templateProcessorContext = TemplateProcessorContext(rule, ruleInvocationTextFormatter.ruleInvocation)

            return templateProcessor.processTemplate(rule.content, templateProcessorContext)
        } else {
            throw IllegalArgumentException("Text formatter ${ruleInvocationTextFormatter.javaClass} is not supported")
        }
    }

    private fun getTemplateProcessor(template: Template<String>): TemplateProcessor<Template<String>, String> {
        return templateProcessors.find { it.templateType.isAssignableFrom(template.javaClass) }
                ?: throw IllegalArgumentException("Cannot find template processor for class ${template.javaClass}")
    }
}