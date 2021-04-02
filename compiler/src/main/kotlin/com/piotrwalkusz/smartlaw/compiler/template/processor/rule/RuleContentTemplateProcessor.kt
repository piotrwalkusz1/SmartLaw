package com.piotrwalkusz.smartlaw.compiler.template.processor.rule

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.converter.elements.FromContractToElementsConverter
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.rule.RuleUtils
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.model.ProcessRuleContentTemplateConfig
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.model.ProcessRuleElementsTemplateConfig
import com.piotrwalkusz.smartlaw.compiler.template.processor.textengine.FreeMarkerTextEngine
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.state.State
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaRuleValue
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.SectionPresentationElement
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.core.model.template.Template
import com.piotrwalkusz.smartlaw.core.model.template.TextEngineTemplate
import freemarker.template.TemplateMethodModel
import java.util.concurrent.atomic.AtomicInteger

class RuleContentTemplateProcessor(
        private val ruleElementsTemplateProcessor: RuleElementsTemplateProcessor,
        templateProcessorService: TemplateProcessorService
) : RuleTemplateProcessor<String, ProcessRuleContentTemplateConfig>(templateProcessorService) {

    fun getConfig(
            presentationElements: List<PresentationElement>,
            ruleProvider: RuleProvider,
            addStyleToContent: Boolean = false
    ): ProcessRuleContentTemplateConfig {
        val linksByElementsIds = getLinksByElementsIds(presentationElements, ruleProvider)
        val fromContractToElementsConverter = FromContractToElementsConverter(ruleProvider, ruleElementsTemplateProcessor)
        val elements = fromContractToElementsConverter.getElements(presentationElements)

        return ProcessRuleContentTemplateConfig(
                elements = elements,
                linksByElementsIds = linksByElementsIds,
                ruleProvider = ruleProvider,
                addStyleToContent = addStyleToContent
        )
    }

    override fun modifyTemplate(template: Template<String>, config: ProcessRuleContentTemplateConfig): Template<String> {
        return if (config.addStyleToContent) {
            addStyleToRuleContentTemplate(template)
        } else {
            template
        }
    }

    override fun getAdditionalContextParameters(config: ProcessRuleContentTemplateConfig): Map<String, Any?> {
        return mapOf(
                "getLinkToElement" to TemplateMethodModel { args -> getLinkToElement(args[0] as String, config.linksByElementsIds) },
                "getDefaultValueOfElement" to TemplateMethodModel { args -> getDefaultValueOfElement(Id(args[0] as String, args[1] as String), config.elements) }
        )
    }

    override fun getAdditionalParametersFromMetaRuleValue(argumentValue: MetaRuleValue, config: ProcessRuleContentTemplateConfig): Map<String, Any?> {
        return mapOf("getContent" to TemplateMethodModel { convertRuleInvocationToNatualLanguage(argumentValue.ruleInvocation, config) })
    }

    private fun convertRuleInvocationToNatualLanguage(ruleInvocation: RuleInvocation, config: ProcessRuleContentTemplateConfig): String {
        val ruleId = ruleInvocation.ruleId
        val rule = config.ruleProvider.getRule(ruleId)
        if (rule == null) {
            Output.get().addError("Rule with id $ruleId does not exist");
            return ""
        }
        val ruleArguments = RuleUtils.associateRuleArgumentsWithValues(rule, ruleInvocation)

        return processTemplate(rule.content, ruleArguments, config)
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

    private fun getLinksByElementsIds(presentationElements: List<PresentationElement>, ruleProvider: RuleProvider): Map<Id, String> {
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
                val ruleInvocation = presentationElement.ruleInvocation
                val ruleId = ruleInvocation.ruleId
                val rule = ruleProvider.getRule(ruleId)
                if (rule == null) {
                    Output.get().addError("Rule with id $ruleId does not exist")
                    return listOf()
                }
                val elementLink = "§ " + nextIndentationIndex.get()
                val ruleArguments = RuleUtils.associateRuleArgumentsWithValues(rule, ruleInvocation)
                val elements = ruleElementsTemplateProcessor.processTemplate(rule.elements, ruleArguments)

                return elements
                        .map { element -> element.id }
                        .map { id -> id to elementLink }
            }
            else ->
                throw IllegalArgumentException("Presentation element ${presentationElement::class.simpleName} is not supported")
        }
    }

    private fun getLinkToElement(elementId: String, linksByElementsIds: Map<Id, String>): String? {
        val linkToElement = linksByElementsIds[convertStringToId(elementId)]
        if (linkToElement == null) {
            Output.get().addError("Element with id \"$elementId\" does not exist")
        }

        return linkToElement
    }

    private fun convertStringToId(idAsString: String): Id {
        return Id(idAsString.substringAfterLast("."), idAsString.substringBeforeLast("."))
    }

    private fun getDefaultValueOfElement(elementId: Id, elements: List<Element>): String? {
        val element = elements.find { it.id == elementId }
        if (element !is State) {
            Output.get().addError("Element with id \"$elementId\" is not State")
            return null
        }
        val defaultValue = element.defaultValue
        if (defaultValue !is MetaPrimitiveValue) {
            Output.get().addError("Default value of element with id \"$elementId\" is not MetaPrimitiveValue")
            return null
        }

        return defaultValue.value
    }
}