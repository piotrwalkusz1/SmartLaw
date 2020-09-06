package com.piotrwalkusz.betterlaw.model.element.proposition

import com.piotrwalkusz.betterlaw.model.common.Id

data class PropositionFunctionVariable(
        val function: Id,
        val variables: List<PropositionVariable>
) : PropositionVariable