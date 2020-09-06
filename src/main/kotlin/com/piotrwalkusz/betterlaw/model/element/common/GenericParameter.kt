package com.piotrwalkusz.betterlaw.model.element.common

import com.piotrwalkusz.betterlaw.model.element.common.type.Type

data class GenericParameter(
        val name: String,
        val baseType: Type
)