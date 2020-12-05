package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageProvision
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.rule.Rule

data class ExtendedRuleInvocationPresentationElement(
        override val presentationElement: RuleInvocationPresentationElement,
        override val naturalLanguageDocumentObject: NaturalLanguageProvision,
        val rule: Rule,
) : ExtendedPresentationElement<RuleInvocationPresentationElement, NaturalLanguageProvision>