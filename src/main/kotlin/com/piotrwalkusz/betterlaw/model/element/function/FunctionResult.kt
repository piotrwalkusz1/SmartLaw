package com.piotrwalkusz.betterlaw.model.element.function

import com.piotrwalkusz.betterlaw.model.element.common.type.Type
import com.piotrwalkusz.betterlaw.model.element.function.statement.Expression

data class FunctionResult(
        val expression: Expression,
        val type: Type
)