package com.piotrwalkusz.smartlaw.model.element.action

import com.piotrwalkusz.smartlaw.model.element.common.type.DefinitionRef

data class ActionArgument(
        val name: String,
        val description: String? = null,
        val type: DefinitionRef
)