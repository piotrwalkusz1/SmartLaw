package com.piotrwalkusz.smartlaw.compiler.validator.model

data class ValidatedAssignment(
        val variable: ValidatedVariableRef,
        val value: ValidatedExpression
) : ValidatedStatement