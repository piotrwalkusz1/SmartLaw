package com.piotrwalkusz.smartlaw.compiler.validator.model

data class ValidatedState(
        val name: String,
        val type: ValidatedType,
        val defaultValue: ValidatedValue?
) : ValidatedElement