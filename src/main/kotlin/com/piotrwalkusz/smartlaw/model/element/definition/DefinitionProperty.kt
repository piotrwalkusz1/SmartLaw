package com.piotrwalkusz.smartlaw.model.element.definition

import com.piotrwalkusz.smartlaw.model.element.common.type.Type

data class DefinitionProperty(
        val name: String,
        val type: Type,
        val description: String? = null
)