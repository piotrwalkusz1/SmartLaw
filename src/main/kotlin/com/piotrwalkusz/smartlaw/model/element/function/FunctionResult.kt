package com.piotrwalkusz.smartlaw.model.element.function

import com.piotrwalkusz.smartlaw.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.model.element.function.statement.Expression

data class FunctionResult(
        val expression: Expression,
        val type: Type
)