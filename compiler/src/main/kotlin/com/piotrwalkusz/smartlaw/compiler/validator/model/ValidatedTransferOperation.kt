package com.piotrwalkusz.smartlaw.compiler.validator.model

data class ValidatedTransferOperation(
        val destination: ValidatedExpression,
        val count: ValidatedExpression
) : ValidatedOperation