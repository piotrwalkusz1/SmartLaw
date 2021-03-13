package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.core.model.element.common.type.TypeTemplate
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Expression

data class FunctionResult(
        val expression: Expression,
        val type: TypeTemplate
)