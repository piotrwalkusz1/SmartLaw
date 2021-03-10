package com.piotrwalkusz.smartlaw.compiler.validator

import com.piotrwalkusz.smartlaw.compiler.meta.MetaTypeService
import com.piotrwalkusz.smartlaw.compiler.rule.RuleUtils
import com.piotrwalkusz.smartlaw.compiler.rule.model.RuleArgumentWithValue
import com.piotrwalkusz.smartlaw.compiler.validator.executor.ValidatorExecutor
import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.core.model.validator.Validator

class ValidatorService(
        private val validatorExecutors: List<ValidatorExecutor<*>> = ValidatorExecutor.DEFAULT_VALIDATOR_EXECUTORS
) {

    fun validateRuleArgumentsValues(ruleArguments: List<RuleArgumentWithValue>): List<RuleArgumentValidationResult> {
        return ruleArguments.map {
            RuleArgumentValidationResult(
                    it.argument,
                    it.value,
                    runValidators(it.argument.validators, it.argument, it.value)
            )
        }
    }

    private fun runValidators(validators: List<Validator>, argument: MetaArgument, value: MetaValue?): List<ValidationResult> {
        if (value == null) {
            return listOf(ValidationResult.error("Value cannot be null"))
        }

        val metaType = MetaTypeService.DEFAULT_META_TYPES.findLast { it.getId() == argument.type }
        if (metaType != null) {
            val error = metaType.validateMetaValue(value)
            if (error != null) {
                return listOf(ValidationResult.error(error))
            }
        }

        return validators.map { runValidator(it, value) }
    }

    private fun runValidator(validator: Validator, value: MetaValue): ValidationResult {
        val validatorExecutor = getValidatorExecutor(validator)

        return validatorExecutor.validate(validator, value)
    }

    private fun <T : Validator> getValidatorExecutor(validator: T): ValidatorExecutor<T> {
        @Suppress("UNCHECKED_CAST")
        return validatorExecutors.find { it.validatorType == validator::class.java } as? ValidatorExecutor<T>
                ?: throw IllegalArgumentException("No validator executor for validator ${validator::class.java}")
    }
}