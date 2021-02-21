package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.*
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.ProcessRuleContentTemplateConfig
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.compiler.validator.ValidatorService
import com.piotrwalkusz.smartlaw.core.model.document.ConvertibleToNaturalLanguage
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.SectionPresentationElement
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation
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
        val context = prepareExtendPresentationElementContext(document.presentationElements)

        return NaturalLanguageDocument(
                title = document.name,
                objects = extendPresentationElements(document.presentationElements, context).map { it.naturalLanguageDocumentObject }
        )
    }

    fun convertRuleInvocationToNatualLanguage(ruleInvocation: RuleInvocation): String {
        val presentationElement = RuleInvocationPresentationElement(ruleInvocation)
        val context = prepareExtendPresentationElementContext(listOf(presentationElement))
        val extendedPresentationElement = extendRuleInvocationPresentationElement(presentationElement, context)

        return extendedPresentationElement.naturalLanguageDocumentObject.content
    }

    fun extendPresentationElements(presentationElements: List<PresentationElement>): List<ExtendedPresentationElement<*, *>> {
        return extendPresentationElements(presentationElements, presentationElements)
    }

    fun extendPresentationElements(allPresentationElements: List<PresentationElement>, presentationElementsToExtend: List<PresentationElement>): List<ExtendedPresentationElement<*, *>> {
        val nextIndentationIndex = AtomicInteger(1)
        val context = prepareExtendPresentationElementContext(allPresentationElements)

        return presentationElementsToExtend.map { extendPresentationElement(it, context, nextIndentationIndex) }
    }

    private fun extendPresentationElements(presentationElements: List<PresentationElement>, context: ExtendPresentationElementContext): List<ExtendedPresentationElement<*, *>> {
        val nextIndentationIndex = AtomicInteger(1)

        return presentationElements.map { extendPresentationElement(it, context, nextIndentationIndex) }
    }

    private fun extendPresentationElement(presentationElement: PresentationElement, context: ExtendPresentationElementContext, nextIndentationIndex: AtomicInteger): ExtendedPresentationElement<*, *> {
        return when (presentationElement) {
            is RuleInvocationPresentationElement -> {
                extendRuleInvocationPresentationElement(presentationElement, context)
            }
            is SectionPresentationElement -> {
                extendSectionPresentationElement(presentationElement, context, nextIndentationIndex)
            }
            else -> {
                throw IllegalArgumentException("Text formatter ${presentationElement.javaClass} is not supported")
            }
        }
    }

    private fun extendSectionPresentationElement(presentationElement: SectionPresentationElement, context: ExtendPresentationElementContext, nextIndentationIndex: AtomicInteger): ExtendedSectionPresentationElement {
        val extendedPresentationElements = presentationElement.presentationElements
                .map { it as RuleInvocationPresentationElement }
                .map { extendRuleInvocationPresentationElement(it, context) }

        return ExtendedSectionPresentationElement(presentationElement, title = "§ " + nextIndentationIndex.getAndIncrement(), extendedPresentationElements)
    }

    private fun extendRuleInvocationPresentationElement(presentationElement: RuleInvocationPresentationElement, context: ExtendPresentationElementContext): ExtendedRuleInvocationPresentationElement {
        val rule = ruleProvider.getRule(presentationElement.ruleInvocation.ruleId)!!
        val processRuleContentTemplateConfig = ProcessRuleContentTemplateConfig(addStyleToContent = config.addStyleToRuleContent)
        val ruleArgumentValidationResults = validatorService.validateRuleArgumentsValues(rule, presentationElement.ruleInvocation)
        val content = templateProcessorService.processRuleContentTemplate(rule.content, ruleArgumentValidationResults, context.linksByElementsIds, processRuleContentTemplateConfig, this::convertRuleInvocationToNatualLanguage)
        val naturalLanguageProvision = NaturalLanguageProvision(content)
        ruleArgumentValidationResults
                .flatMap { it.results }
                .mapNotNull { it.error }
                .forEach { Output.get().addError(it) }

        return ExtendedRuleInvocationPresentationElement(presentationElement, naturalLanguageProvision, rule, ruleArgumentValidationResults.map { it.argument.name to it.results }.toMap())
    }

    private fun prepareExtendPresentationElementContext(presentationElements: List<PresentationElement>): ExtendPresentationElementContext {
        val linksByElementsIds = templateProcessorService.getLinksByElementsIds(presentationElements, ruleProvider)

        return ExtendPresentationElementContext(linksByElementsIds)
    }
}