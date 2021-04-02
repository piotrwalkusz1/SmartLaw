package com.piotrwalkusz.smartlaw.compiler.validator.model

data class ValidatedEqualsOperation(
        val firstOperand: ValidatedExpression,
        val secondOperand: ValidatedExpression
) : ValidatedOperation