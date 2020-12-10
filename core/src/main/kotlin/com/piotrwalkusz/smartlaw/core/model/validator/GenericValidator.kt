package com.piotrwalkusz.smartlaw.core.model.validator

data class GenericValidator(
        val expressionEvaluatorType: String,
        val expression: String
) : Validator