package com.piotrwalkusz.betterlaw.model.element.proposition

import com.piotrwalkusz.betterlaw.model.common.Id

data class PropositionPrimitiveVariable(
        val type: Id,
        val value: String
) : PropositionVariable