package com.piotrwalkusz.smartlaw.core.model.element.common.type

import com.piotrwalkusz.smartlaw.core.model.common.Id

data class DefinitionRef(
        val definition: Id,
        val parameters: List<TypeTemplate> = listOf()
) : Type