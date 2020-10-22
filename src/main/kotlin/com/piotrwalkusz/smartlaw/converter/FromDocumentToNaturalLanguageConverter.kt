package com.piotrwalkusz.smartlaw.converter

import com.piotrwalkusz.smartlaw.converter.naturallanguage.model.NaturalLanguageDocument
import com.piotrwalkusz.smartlaw.converter.naturallanguage.model.NaturalLanguageDocumentObject
import com.piotrwalkusz.smartlaw.converter.naturallanguage.model.NaturalLanguageProvision
import com.piotrwalkusz.smartlaw.converter.naturallanguage.model.NaturalLanguageSection
import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.document.ConvertibleToNaturalLanguage
import com.piotrwalkusz.smartlaw.model.rule.textformatter.IndentationRuleInvocationTextFormatter
import com.piotrwalkusz.smartlaw.model.rule.textformatter.RuleInvocationTextFormatter
import com.piotrwalkusz.smartlaw.model.rule.textformatter.SimpleRuleInvocationTextFormatter
import com.piotrwalkusz.smartlaw.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.model.template.Template
import com.piotrwalkusz.smartlaw.provider.RuleProvider
import com.piotrwalkusz.smartlaw.template.RuleInvocationTemplateProcessorContext
import com.piotrwalkusz.smartlaw.template.TemplateProcessor


class FromDocumentToNaturalLanguageConverter(
        private val ruleProvider: RuleProvider,
        private val templateProcessors: List<TemplateProcessor<Template<String>, String>>
) {

    fun convert(document: ConvertibleToNaturalLanguage): NaturalLanguageDocument {
        val linksByElementsIds = document.ruleInvocationTextFormatters
                .filterIsInstance(IndentationRuleInvocationTextFormatter::class.java)
                .mapIndexed { index, section ->
                    section.ruleInvocations
                            .map { it as SimpleRuleInvocationTextFormatter }
                            .map { ruleProvider.getRule(it.ruleInvocation.ruleId)!! }
                            .flatMap { rule -> rule.elements }
                            .map { element -> (element.id as StaticTemplate<Id>).value }
                            .map { id -> id to "§ ${index+1}" }
                }
                .flatten()
                .toMap()

        return NaturalLanguageDocument(
                title = document.name,
                objects = convertRuleInvocationsToNaturalLanguageDocumentObjects(document.ruleInvocationTextFormatters, linksByElementsIds)
        )
    }

    private fun convertRuleInvocationsToNaturalLanguageDocumentObjects(ruleInvocationTextFormatter: List<RuleInvocationTextFormatter>, linksByElementsIds: Map<Id, String>): List<NaturalLanguageDocumentObject> {
        return ruleInvocationTextFormatter.map { convertRuleInvocationToNaturalLanguageDocumentObject(it, linksByElementsIds) }
    }

    private fun convertRuleInvocationToNaturalLanguageDocumentObject(ruleInvocationTextFormatter: RuleInvocationTextFormatter, linksByElementsIds: Map<Id, String>): NaturalLanguageDocumentObject {
        return when (ruleInvocationTextFormatter) {
            is SimpleRuleInvocationTextFormatter -> {
                convertSimpleRuleInvocationTextFormatterToNaturalLanguage(ruleInvocationTextFormatter, linksByElementsIds)
            }
            is IndentationRuleInvocationTextFormatter -> {
                convertIndentationRuleInvocationTextFormatterToNaturalLanguage(ruleInvocationTextFormatter, linksByElementsIds)
            }
            else -> {
                throw IllegalArgumentException("Text formatter ${ruleInvocationTextFormatter.javaClass} is not supported")
            }
        }
    }

    private fun convertIndentationRuleInvocationTextFormatterToNaturalLanguage(ruleInvocationTextFormatter: IndentationRuleInvocationTextFormatter, linksByElementsIds: Map<Id, String>): NaturalLanguageSection {
        val provisions = ruleInvocationTextFormatter.ruleInvocations
                .map { it as SimpleRuleInvocationTextFormatter }
                .map { convertSimpleRuleInvocationTextFormatterToNaturalLanguage(it, linksByElementsIds) }

        return NaturalLanguageSection(provisions)
    }

    private fun convertSimpleRuleInvocationTextFormatterToNaturalLanguage(ruleInvocationTextFormatter: SimpleRuleInvocationTextFormatter, linksByElementsIds: Map<Id, String>): NaturalLanguageProvision {
        val rule = ruleProvider.getRule(ruleInvocationTextFormatter.ruleInvocation.ruleId)!!
        val templateProcessor = getTemplateProcessor(rule.content)
        val templateProcessorContext = RuleInvocationTemplateProcessorContext(rule, ruleInvocationTextFormatter.ruleInvocation, linksByElementsIds)
        val content = templateProcessor.processTemplate(rule.content, templateProcessorContext)

        return NaturalLanguageProvision(content)
    }

    private fun getTemplateProcessor(template: Template<String>): TemplateProcessor<Template<String>, String> {
        return templateProcessors.find { it.templateType.isAssignableFrom(template.javaClass) }
                ?: throw IllegalArgumentException("Cannot find template processor for class ${template.javaClass}")
    }
}