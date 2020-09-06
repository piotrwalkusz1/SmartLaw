package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.common.Id

data class PropositionFunctionVariable(
        val function: Id,
        val variables: List<PropositionVariable>
) : PropositionVariable