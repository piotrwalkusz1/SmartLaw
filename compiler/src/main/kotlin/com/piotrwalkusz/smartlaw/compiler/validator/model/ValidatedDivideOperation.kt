package com.piotrwalkusz.smartlaw.compiler.validator.model

data class ValidatedDivideOperation(
        val firstOperand: ValidatedExpression,
        val secondOperand: ValidatedExpression
) : ValidatedOperation