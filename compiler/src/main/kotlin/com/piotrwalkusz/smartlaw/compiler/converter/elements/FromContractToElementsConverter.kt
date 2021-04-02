package com.piotrwalkusz.smartlaw.compiler.converter.elements

import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.rule.RuleUtils
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleElementsTemplateProcessor
import com.piotrwalkusz.smartlaw.core.model.document.Contract
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.SectionPresentationElement

class FromContractToElementsConverter(
        private val ruleProvider: RuleProvider,
        private val ruleElementsTemplateProcessor: RuleElementsTemplateProcessor
) {

    fun getElements(contract: Contract): List<Element> {
        return getElements(contract.presentationElements)
    }

    fun getElements(presentationElements: List<PresentationElement>): List<Element> {
        return presentationElements.flatMap { getElements(it) }
    }

    private fun getElements(presentationElement: PresentationElement): List<Element> {
        return when (presentationElement) {
            is SectionPresentationElement -> {
                getElements(presentationElement)
            }
            is RuleInvocationPresentationElement -> {
                getElements(presentationElement)
            }
            else -> {
                throw IllegalArgumentException("PresentationElement class ${presentationElement::class.java} is not supported")
            }
        }
    }

    private fun getElements(presentationElement: SectionPresentationElement): List<Element> {
        return getElements(presentationElement.presentationElements)
    }

    private fun getElements(presentationElement: RuleInvocationPresentationElement): List<Element> {
        val ruleInvocation = presentationElement.ruleInvocation
        val ruleId = presentationElement.ruleInvocation.ruleId
        val rule = ruleProvider.getRule(ruleId) ?: throw IllegalArgumentException("No rule with id $ruleId")
        val ruleArguments = RuleUtils.associateRuleArgumentsWithValues(rule, ruleInvocation)

        return ruleElementsTemplateProcessor.processTemplate(rule.elements, ruleArguments)
    }
}