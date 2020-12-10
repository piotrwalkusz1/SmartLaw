package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.*
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.ProcessRuleContentTemplateConfig
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.compiler.validator.ValidatorService
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.document.ConvertibleToNaturalLanguage
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.SectionPresentationElement
import java.util.concurrent.atomic.AtomicInteger

class FromDocumentToNaturalLanguageConverter(
        private val ruleProvider: RuleProvider,
        private val templateProcessorService: TemplateProcessorService,
        private val config: Config,
        private val validatorService: ValidatorService
) {

    data class Config(
            val addStyleToRuleContent: Boolean
    )

    fun convert(document: ConvertibleToNaturalLanguage): NaturalLanguageDocument {
        val linksByElementsIds = templateProcessorService.getLinksByElementsIds(document, ruleProvider)

        return NaturalLanguageDocument(
                title = document.name,
                objects = extendPresentationElements(document.presentationElements, linksByElementsIds).map { it.naturalLanguageDocumentObject }
        )
    }

    fun extendPresentationElements(presentationElements: List<PresentationElement>): List<ExtendedPresentationElement<*, *>> {
        val nextIndentationIndex = AtomicInteger(1)
        val linksByElementsIds = templateProcessorService.getLinksByElementsIds(presentationElements, ruleProvider)

        return presentationElements.map { extendPresentationElement(it, linksByElementsIds, nextIndentationIndex) }
    }

    private fun extendPresentationElements(presentationElements: List<PresentationElement>, linksByElementsIds: Map<Id, String>): List<ExtendedPresentationElement<*, *>> {
        val nextIndentationIndex = AtomicInteger(1)

        return presentationElements.map { extendPresentationElement(it, linksByElementsIds, nextIndentationIndex) }
    }

    private fun extendPresentationElement(presentationElement: PresentationElement, linksByElementsIds: Map<Id, String>, nextIndentationIndex: AtomicInteger): ExtendedPresentationElement<*, *> {
        return when (presentationElement) {
            is RuleInvocationPresentationElement -> {
                extendRuleInvocationPresentationElement(presentationElement, linksByElementsIds)
            }
            is SectionPresentationElement -> {
                extendSectionPresentationElement(presentationElement, linksByElementsIds, nextIndentationIndex)
            }
            else -> {
                throw IllegalArgumentException("Text formatter ${presentationElement.javaClass} is not supported")
            }
        }
    }

    private fun extendSectionPresentationElement(presentationElement: SectionPresentationElement, linksByElementsIds: Map<Id, String>, nextIndentationIndex: AtomicInteger): ExtendedSectionPresentationElement {
        val extendedPresentationElements = presentationElement.presentationElements
                .map { it as RuleInvocationPresentationElement }
                .map { extendRuleInvocationPresentationElement(it, linksByElementsIds) }

        return ExtendedSectionPresentationElement(presentationElement, title = "§ " + nextIndentationIndex.getAndIncrement(), extendedPresentationElements)
    }

    private fun extendRuleInvocationPresentationElement(presentationElement: RuleInvocationPresentationElement, linksByElementsIds: Map<Id, String>): ExtendedRuleInvocationPresentationElement {
        val rule = ruleProvider.getRule(presentationElement.ruleInvocation.ruleId)!!
        val processRuleContentTemplateConfig = ProcessRuleContentTemplateConfig(addStyleToContent = config.addStyleToRuleContent)
        val ruleArgumentValidationResults = validatorService.validateRuleArgumentsValues(rule, presentationElement.ruleInvocation)
        val content = templateProcessorService.processRuleContentTemplate(rule.content, ruleArgumentValidationResults, linksByElementsIds, processRuleContentTemplateConfig)
        val naturalLanguageProvision = NaturalLanguageProvision(content)

        return ExtendedRuleInvocationPresentationElement(presentationElement, naturalLanguageProvision, rule, ruleArgumentValidationResults.map { it.results })
    }
}