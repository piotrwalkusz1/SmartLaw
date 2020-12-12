package com.piotrwalkusz.smartlaw.compiler.validator.executor

import com.piotrwalkusz.smartlaw.compiler.validator.ValidationResult
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.validator.NumberRangeValidator

class NumberRangeValidatorExecutor : ValidatorExecutor<NumberRangeValidator> {

    override val validatorType: Class<NumberRangeValidator>
        get() = NumberRangeValidator::class.java

    override fun validate(validator: NumberRangeValidator, value: MetaValue): ValidationResult {
        if (value is MetaPrimitiveValue) {
            val number = value.value.toIntOrNull()
            if (number != null) {
                if (number > validator.maxValue || number < validator.minValue) {
                    return ValidationResult.error("Value must be between ${validator.minValue} and ${validator.maxValue}")
                } else {
                    return ValidationResult.ok();
                }
            } else {
                return ValidationResult.error("Cannot parse as number")
            }
        } else {
            return ValidationResult.error("Expected type PrimitiveValue but was ${value::class.simpleName}")
        }
    }
}