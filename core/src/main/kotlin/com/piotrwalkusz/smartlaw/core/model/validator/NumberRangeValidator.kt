package com.piotrwalkusz.smartlaw.core.model.validator

data class NumberRangeValidator(
        val minValue: Int,
        val maxValue: Int
) : Validator