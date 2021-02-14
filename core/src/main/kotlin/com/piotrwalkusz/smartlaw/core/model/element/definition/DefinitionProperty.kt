package com.piotrwalkusz.smartlaw.core.model.element.definition

import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type

data class DefinitionProperty(
        val name: String,
        val type: Type,
        val description: String?
)