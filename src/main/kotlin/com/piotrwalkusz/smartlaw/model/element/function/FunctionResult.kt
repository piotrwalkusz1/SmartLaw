package com.piotrwalkusz.smartlaw.model.element.function

import com.piotrwalkusz.smartlaw.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.model.element.function.statement.Expression
import com.piotrwalkusz.smartlaw.model.template.Template

data class FunctionResult(
        val expression: Template<Expression>,
        val type: Template<Type>
)