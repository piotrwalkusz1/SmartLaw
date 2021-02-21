package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.context.RuleInvocationTemplateProcessorContext
import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.compiler.template.processor.textengine.FreeMarkerTextEngine
import com.piotrwalkusz.smartlaw.compiler.validator.RuleArgumentValidationResult
import com.piotrwalkusz.smartlaw.compiler.validator.ValidatorService
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.document.ConvertibleToNaturalLanguage
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.presentation.SectionPresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template
import com.piotrwalkusz.smartlaw.core.model.template.TextEngineTemplate
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Function

class TemplateProcessorService(
        private val templateProcessors: List<TemplateProcessor<*, *>> = TemplateProcessor.DEFAULT_TEMPLATE_PROCESSORS,
) {

    fun processRuleContentTemplate(
            content: Template<String>,
            ruleArgumentValidationResults: List<RuleArgumentValidationResult>,
            linksByElementsIds: Map<Id, String>,
            config: ProcessRuleContentTemplateConfig,
            convertRuleInvocationToNatualLanguage: Function<RuleInvocation, String>
    ): String {
        val templateProcessorContext = RuleInvocationTemplateProcessorContext(ruleArgumentValidationResults, linksByElementsIds, convertRuleInvocationToNatualLanguage)
        val ruleContentTemplate = if (config.addStyleToContent) addStyleToRuleContentTemplate(content) else content

        return processTemplate(ruleContentTemplate, templateProcessorContext)
    }

    private fun <T> processTemplate(template: Template<T>, templateProcessorContext: TemplateProcessorContext): T {
        val templateProcessor = getTemplateProcessorForTemplate(template)

        return templateProcessor.processTemplate(template, templateProcessorContext)
    }

    fun getLinksByElementsIds(document: ConvertibleToNaturalLanguage, ruleProvider: RuleProvider): Map<Id, String> {
        return getLinksByElementsIds(document.presentationElements, ruleProvider).toMap()
    }

    fun getLinksByElementsIds(presentationElements: List<PresentationElement>, ruleProvider: RuleProvider): Map<Id, String> {
        val nextIndentationIndex = AtomicInteger(0);

        return presentationElements
                .flatMap { presentationElement -> getLinksByElementsIds(presentationElement, ruleProvider, nextIndentationIndex) }
                .toMap()
    }

    private fun getLinksByElementsIds(presentationElement: PresentationElement, ruleProvider: RuleProvider, nextIndentationIndex: AtomicInteger): List<Pair<Id, String>> {
        return when (presentationElement) {
            is SectionPresentationElement -> {
                nextIndentationIndex.incrementAndGet()
                presentationElement.presentationElements.flatMap { nestedPresentationElement -> getLinksByElementsIds(nestedPresentationElement, ruleProvider, nextIndentationIndex) }
            }
            is RuleInvocationPresentationElement -> {
                val ruleId = presentationElement.ruleInvocation.ruleId
                val rule = ruleProvider.getRule(ruleId)
                if (rule == null) {
                    Output.get().addError("Rule with id $ruleId does not exist")
                    return listOf()
                }
                val elementLink = "§ " + nextIndentationIndex.get()
                val elements = rule.elements
                if (elements !is StaticTemplate) {
                    Output.get().addError("Template other than StaticTemplate is not supported for elements")
                    return listOf()
                }

                return elements.value
                        .map { element -> (element.id) } // TODO: Support dynamic element id
                        .map { id -> id to elementLink }
            }
            else ->
                throw IllegalArgumentException("Presentation element ${presentationElement::class.simpleName} is not supported")
        }
    }

    private fun <T> getTemplateProcessorForTemplate(template: Template<T>): TemplateProcessor<Template<T>, T> {
        @Suppress("UNCHECKED_CAST")
        return templateProcessors.find { it.templateType.isAssignableFrom(template.javaClass) } as TemplateProcessor<Template<T>, T>?
                ?: throw IllegalArgumentException("Cannot find template processor for class ${template.javaClass}")
    }

    private fun addStyleToRuleContentTemplate(ruleContentTemplate: Template<String>): Template<String> {
        return when (ruleContentTemplate) {
            is TextEngineTemplate -> {
                return when (ruleContentTemplate.type) {
                    FreeMarkerTextEngine.TEMPLATE_TYPE -> {
                        ruleContentTemplate.copy(
                                template = Regex("""\$\{[^$]*}""").replace(ruleContentTemplate.template) { "<b>${it.value}</b>" }
                        )
                    }
                    else -> ruleContentTemplate
                }
            }
            else -> ruleContentTemplate
        }
    }
}