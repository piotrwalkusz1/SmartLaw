package com.piotrwalkusz.smartlaw.compiler.validator.model

data class ValidatedFunction(
        val name: String,
        val body: List<ValidatedStatement>,
) : ValidatedElement