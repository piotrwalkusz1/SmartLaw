package com.piotrwalkusz.betterlaw.model.element.common.type

import com.piotrwalkusz.betterlaw.model.common.Id

data class DefinitionRef(
        val definition: Id,
        val parameters: List<Type>
) : Type