package com.piotrwalkusz.smartlaw.compiler.validator.model

data class ValidatedSimpleVariableRef(
        val name: String,
        val type: ValidatedType
) : ValidatedVariableRef