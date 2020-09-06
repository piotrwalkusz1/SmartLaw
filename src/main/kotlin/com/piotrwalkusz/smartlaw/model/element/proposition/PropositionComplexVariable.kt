package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.common.Id

data class PropositionComplexVariable(
        val definition: Id,
        val variables: List<PropositionVariable> = listOf()
) : PropositionVariable