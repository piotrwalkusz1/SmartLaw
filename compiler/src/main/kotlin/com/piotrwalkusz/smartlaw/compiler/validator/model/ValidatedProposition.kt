package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.element.proposition.Proposition

data class ValidatedProposition(
        val proposition: Proposition,
        val head: List<ValidatedPropositionTerm>
) : ValidatedElement