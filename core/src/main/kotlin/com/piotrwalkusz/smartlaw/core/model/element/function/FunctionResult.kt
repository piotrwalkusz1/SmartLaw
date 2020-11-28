package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Expression
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class FunctionResult(
        val expression: Template<Expression>,
        val type: Template<Type>
)