package com.piotrwalkusz.smartlaw.compiler.validator.executor

import com.piotrwalkusz.smartlaw.compiler.validator.ValidationResult
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.validator.GenericValidator
import com.piotrwalkusz.smartlaw.core.model.validator.RegexValidator

class RegexValidatorExecutor : ValidatorExecutor<RegexValidator> {

    override val validatorType: Class<RegexValidator>
        get() = RegexValidator::class.java

    override fun validate(validator: RegexValidator, value: MetaValue): ValidationResult {
        return if (value is MetaPrimitiveValue) {
            val valid = validator.regex.toRegex().matchEntire(value.value) != null
            if (valid) ValidationResult.ok() else ValidationResult("Value \"${value.value}\" does not match to regex \"${validator.regex}\"")
        } else {
            ValidationResult.error("Expected type PrimitiveValue but was ${value::class.simpleName}")
        }
    }
}