package com.piotrwalkusz.smartlaw.compiler.validator.executor

import com.piotrwalkusz.smartlaw.compiler.validator.ValidationResult
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.validator.Validator

interface ValidatorExecutor<T : Validator> {

    companion object {
        val DEFAULT_VALIDATOR_EXECUTORS = listOf<ValidatorExecutor<*>>(
                RegexValidatorExecutor(),
                GenericValidatorExecutor(),
                NumberRangeValidatorExecutor()
        )
    }

    val validatorType: Class<T>

    fun validate(validator: T, value: MetaValue): ValidationResult
}