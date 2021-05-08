package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.element.definition.Definition

data class ValidatedPropositionComplexVariable(
        val definition: Definition,
        val variables: List<ValidatedPropositionVariable>
) : ValidatedPropositionTerm