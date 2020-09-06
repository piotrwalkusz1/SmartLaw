package com.piotrwalkusz.smartlaw.model.element.common.type

import com.piotrwalkusz.smartlaw.model.common.Id

data class DefinitionRef(
        val definition: Id,
        val parameters: List<Type>
) : Type