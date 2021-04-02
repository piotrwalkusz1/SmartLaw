package com.piotrwalkusz.smartlaw.compiler.validator.model

data class ValidatedMultiplyOperation(
        val firstOperand: ValidatedExpression,
        val secondOperand: ValidatedExpression
) : ValidatedOperation