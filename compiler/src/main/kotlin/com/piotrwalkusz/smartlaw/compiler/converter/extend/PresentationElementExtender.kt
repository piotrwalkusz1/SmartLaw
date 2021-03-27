package com.piotrwalkusz.smartlaw.compiler.converter.extend

import com.github.michaelbull.result.getErrorOr
import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageProvision
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.rule.RuleUtils
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleContentTemplateProcessor
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleElementsTemplateProcessor
import com.piotrwalkusz.smartlaw.compiler.validator.ElementValidatorService
import com.piotrwalkusz.smartlaw.compiler.validator.RuleArgumentsValuesValidatorService
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.SectionPresentationElement
import java.util.concurrent.atomic.AtomicInteger

class PresentationElementExtender(
        private val ruleProvider: RuleProvider,
        private val config: Config,
        private val ruleArgumentsValuesValidatorService: RuleArgumentsValuesValidatorService,
        private val ruleContentTemplateProcessor: RuleContentTemplateProcessor,
        private val ruleElementsTemplateProcessor: RuleElementsTemplateProcessor,
        private val elementValidatorService: ElementValidatorService
) {

    data class Config(
            val addStyleToRuleContent: Boolean,
            val validateElements: Boolean = false
    )

    fun extendPresentationElements(allPresentationElements: List<PresentationElement>): List<ExtendedPresentationElement<*, *>> {
        return extendPresentationElements(allPresentationElements, allPresentationElements)
    }

    fun extendPresentationElements(allPresentationElements: List<PresentationElement>, presentationElementsToExtend: List<PresentationElement>): List<ExtendedPresentationElement<*, *>> {
        val nextIndentationIndex = AtomicInteger(1)
        val context = prepareExtendPresentationElementContext(allPresentationElements)
        val extendedPresentationElements = presentationElementsToExtend.map { extendPresentationElement(it, context, nextIndentationIndex) }

        if (config.validateElements) {
            context.elementsToValidate.forEach { (element, extendedPresentationElement) ->
                val otherElements = context.elements.filter { it !== element }
                val errors = elementValidatorService.validateElement(element, otherElements).getErrorOr(emptyList())
                extendedPresentationElement.elementValidationErrors.addAll(errors)
                errors.forEach { Output.get().addError(it.message) }
            }
        }

        return extendedPresentationElements;
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
        val rule = ruleProvider.getRule(presentationElement.ruleInvocation.ruleId)
        if (rule == null) {
            Output.get().addError("Rule with id ${presentationElement.ruleInvocation.ruleId} does not exist");
            return ExtendedRuleInvocationPresentationElement(presentationElement, NaturalLanguageProvision("ERROR"), rule, emptyMap())
        }
        val ruleArguments = RuleUtils.associateRuleArgumentsWithValues(rule, presentationElement.ruleInvocation)
        val ruleArgumentValidationResults = ruleArgumentsValuesValidatorService.validateRuleArgumentsValues(ruleArguments)
        val content = ruleContentTemplateProcessor.processTemplate(rule.content, ruleArguments, context.processRuleContentTemplateConfig)
        val naturalLanguageProvision = NaturalLanguageProvision(content)
        ruleArgumentValidationResults
                .flatMap { it.results }
                .mapNotNull { it.error }
                .forEach { Output.get().addError(it) }

        val extendedRuleInvocationPresentationElement = ExtendedRuleInvocationPresentationElement(
                presentationElement,
                naturalLanguageProvision,
                rule,
                ruleArgumentValidationResults.map { it.argument.name to it.results }.toMap()
        )

        if (config.validateElements) {
            val elements = ruleElementsTemplateProcessor.processTemplate(rule.elements, ruleArguments)
            context.elements.addAll(elements)
            context.elementsToValidate.addAll(elements.map { it to extendedRuleInvocationPresentationElement })
        }

        return extendedRuleInvocationPresentationElement
    }

    private fun prepareExtendPresentationElementContext(presentationElements: List<PresentationElement>): ExtendPresentationElementContext {
        val processRuleContentTemplateConfig = ruleContentTemplateProcessor.getConfig(
                presentationElements,
                ruleProvider,
                addStyleToContent = config.addStyleToRuleContent
        )

        return ExtendPresentationElementContext(processRuleContentTemplateConfig)
    }
}