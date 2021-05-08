package com.piotrwalkusz.smartlaw.compiler.converter.logic

import com.piotrwalkusz.smartlaw.compiler.converter.elements.FromContractToElementsConverter
import com.piotrwalkusz.smartlaw.compiler.validator.ElementValidatorService
import com.piotrwalkusz.smartlaw.compiler.validator.model.ValidatedProposition
import com.piotrwalkusz.smartlaw.compiler.validator.model.ValidatedPropositionComplexVariable
import com.piotrwalkusz.smartlaw.compiler.validator.model.ValidatedPropositionStateVariable
import com.piotrwalkusz.smartlaw.compiler.validator.model.ValidatedPropositionVariable
import com.piotrwalkusz.smartlaw.core.model.document.Contract
import com.piotrwalkusz.smartlaw.core.model.element.Element
import net.pearx.kasechange.toCamelCase
import org.apache.commons.lang3.StringUtils

class FromContractToLogicRulesConverter(
        private val fromContractToElementsConverter: FromContractToElementsConverter,
        private val elementValidatorService: ElementValidatorService
) {

    fun convert(contract: Contract, externalElements: List<Element>): String {
        val elements = fromContractToElementsConverter.getElements(contract)
        val validatedElements = elementValidatorService.validateElements(elements, externalElements)

        return validatedElements
                .filterIsInstance<ValidatedProposition>().joinToString("\n") { processProposition(it) }
    }

    private fun processProposition(proposition: ValidatedProposition): String {
        return proposition.head.joinToString(", ") { processPropositionVariable(it) } + "."
    }

    private fun processPropositionVariable(propositionVariable: ValidatedPropositionVariable): String {
        if (propositionVariable is ValidatedPropositionComplexVariable) {
            return processPropositionComplexVariable(propositionVariable)
        } else if (propositionVariable is ValidatedPropositionStateVariable) {
            return processPropositionStateVariable(propositionVariable)
        } else {
            throw IllegalAccessException("Expected ValidatedPropositionComplexVariable or ValidatedPropositionStateVariable")
        }
    }

    private fun processPropositionComplexVariable(propositionVariable: ValidatedPropositionComplexVariable): String {
        return StringUtils.stripAccents(propositionVariable.definition.name).toCamelCase() +
                "(" +
                propositionVariable.variables.joinToString(", ") { processPropositionVariable(it) } +
                ")"
    }

    private fun processPropositionStateVariable(propositionVariable: ValidatedPropositionStateVariable): String {
        return StringUtils.stripAccents(propositionVariable.state.name).toCamelCase()
    }
}