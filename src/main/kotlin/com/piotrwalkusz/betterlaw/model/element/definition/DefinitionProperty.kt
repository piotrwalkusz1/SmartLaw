package com.piotrwalkusz.betterlaw.model.element.definition

import com.piotrwalkusz.betterlaw.model.element.common.type.Type

data class DefinitionProperty(
        val name: String,
        val type: Type,
        val description: String? = null
)