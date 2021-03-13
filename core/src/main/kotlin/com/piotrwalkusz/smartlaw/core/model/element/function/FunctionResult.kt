package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Expression

@GenerateTemplate
data class FunctionResult(
        val expression: Expression,
        val type: Type
)