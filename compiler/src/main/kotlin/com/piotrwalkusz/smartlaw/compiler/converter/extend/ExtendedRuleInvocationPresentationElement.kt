package com.piotrwalkusz.smartlaw.compiler.converter.extend

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageProvision
import com.piotrwalkusz.smartlaw.compiler.validator.ElementValidationError
import com.piotrwalkusz.smartlaw.compiler.validator.ValidationResult
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.rule.Rule

data class ExtendedRuleInvocationPresentationElement(
        override val presentationElement: RuleInvocationPresentationElement,
        override val naturalLanguageDocumentObject: NaturalLanguageProvision,
        val rule: Rule?,
        val validationResults: Map<String, List<ValidationResult>>,
        val elementValidationErrors: MutableList<ElementValidationError> = mutableListOf()
) : ExtendedPresentationElement<RuleInvocationPresentationElement, NaturalLanguageProvision>