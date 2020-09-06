package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.common.Id

data class PropositionPrimitiveVariable(
        val type: Id,
        val value: String
) : PropositionVariable