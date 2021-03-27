package com.piotrwalkusz.smartlaw.compiler.validator.model

data class ValidatedEnumDefinition(
        val name: String,
        val variants: List<String>
) : ValidatedElement