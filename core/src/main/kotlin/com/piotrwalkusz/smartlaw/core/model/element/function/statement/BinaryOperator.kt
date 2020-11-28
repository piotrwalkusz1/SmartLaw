package com.piotrwalkusz.smartlaw.core.model.element.function.statement

import com.piotrwalkusz.smartlaw.core.model.template.Template

data class BinaryOperator(
        val operator: Template<String>,
        val firstOperand: Template<Expression>,
        val secondOperand: Template<Expression>
)