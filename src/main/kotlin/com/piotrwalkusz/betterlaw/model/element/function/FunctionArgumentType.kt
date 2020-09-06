package com.piotrwalkusz.betterlaw.model.element.function

import com.piotrwalkusz.betterlaw.model.element.common.type.Type

data class FunctionArgumentType(
        val name: String,
        val description: String? = null,
        val type: Type
)