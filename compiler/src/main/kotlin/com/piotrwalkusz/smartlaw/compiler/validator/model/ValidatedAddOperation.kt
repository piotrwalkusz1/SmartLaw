package com.piotrwalkusz.smartlaw.compiler.validator.model

class ValidatedAddOperation(
        firstOperand: ValidatedExpression,
        secondOperand: ValidatedExpression
) : ValidatedMathOperation(firstOperand, secondOperand) {

    override fun getOperator(): String {
        return "+"
    }
}