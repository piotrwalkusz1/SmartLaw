package com.piotrwalkusz.smartlaw.compiler.validator.model

data class ValidatedDefinitionProperty(
        val name: String,
        val type: ValidatedType
) : ValidatedElement