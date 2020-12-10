package com.piotrwalkusz.smartlaw.compiler.validator

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.validator.executor.ValidatorExecutor
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.core.model.validator.Validator

class ValidatorService(
        private val validatorExecutors: List<ValidatorExecutor<*>> = ValidatorExecutor.DEFAULT_VALIDATOR_EXECUTORS
) {

    fun validateRuleArgumentsValues(rule: Rule, ruleInvocation: RuleInvocation): List<RuleArgumentValidationResult> {
        if (rule.arguments.size != ruleInvocation.arguments.size) {
            Output.get().addError("Count of arguments in rule and rule's invocation must be equal. Rule has" +
                    "${rule.arguments.size} arguments rule invocation has ${ruleInvocation.arguments.size} arguments.")
        }

        return rule.arguments
                .mapIndexed { index, argument -> argument to ruleInvocation.arguments.getOrNull(index) }
                .map { RuleArgumentValidationResult(it.first, it.second, runValidators(it.first.validators, it.second)) }
    }

    private fun runValidators(validators: List<Validator>, value: MetaValue?): List<ValidationResult> {
        return validators.map { runValidator(it, value) }
    }

    private fun runValidator(validator: Validator, value: MetaValue?): ValidationResult {
        if (value == null) {
            return ValidationResult.error("Value cannot be null")
        }

        val validatorExecutor = getValidatorExecutor(validator)

        return validatorExecutor.validate(validator, value)
    }

    private fun <T : Validator> getValidatorExecutor(validator: T): ValidatorExecutor<T> {
        @Suppress("UNCHECKED_CAST")
        return validatorExecutors.find { it.validatorType == validator::class.java } as? ValidatorExecutor<T>
                ?: throw IllegalArgumentException("No validator executor for validator ${validator::class.java}")
    }

}