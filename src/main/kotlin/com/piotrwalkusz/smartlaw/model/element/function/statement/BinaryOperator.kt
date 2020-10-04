package com.piotrwalkusz.smartlaw.model.element.function.statement

import com.piotrwalkusz.smartlaw.model.template.Template

data class BinaryOperator(
        val operator: Template<String>,
        val firstOperand: Template<Expression>,
        val secondOperand: Template<Expression>
)