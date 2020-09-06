package com.piotrwalkusz.betterlaw.model.element.proposition

import com.piotrwalkusz.betterlaw.model.common.Id

data class PropositionComplexVariable(
        val definition: Id,
        val variables: List<PropositionVariable> = listOf()
) : PropositionVariable