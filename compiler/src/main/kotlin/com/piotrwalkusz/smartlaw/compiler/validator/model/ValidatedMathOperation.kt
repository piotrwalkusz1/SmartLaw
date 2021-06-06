package com.piotrwalkusz.smartlaw.compiler.validator.model

abstract class ValidatedMathOperation(
        val firstOperand: ValidatedExpression,
        val secondOperand: ValidatedExpression
) : ValidatedOperation {

    abstract fun getOperator(): String
}