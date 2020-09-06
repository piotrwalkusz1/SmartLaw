package com.piotrwalkusz.betterlaw.model.element.action

import com.piotrwalkusz.betterlaw.model.element.common.type.DefinitionRef

data class ActionArgument(
        val name: String,
        val description: String? = null,
        val type: DefinitionRef
)