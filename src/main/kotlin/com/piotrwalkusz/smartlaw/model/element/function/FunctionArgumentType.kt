package com.piotrwalkusz.smartlaw.model.element.function

import com.piotrwalkusz.smartlaw.model.element.common.type.Type

data class FunctionArgumentType(
        val name: String,
        val description: String? = null,
        val type: Type
)