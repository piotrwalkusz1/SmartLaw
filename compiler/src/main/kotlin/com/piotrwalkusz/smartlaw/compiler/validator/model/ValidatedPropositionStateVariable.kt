package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.element.state.State

data class ValidatedPropositionStateVariable(
        val state: State
) : ValidatedPropositionTerm