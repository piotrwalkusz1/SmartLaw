package com.piotrwalkusz.smartlaw.compiler.validator

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.validator.executor.ValidatorExecutor
import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.core.model.validator.Validator
import com.sun.jdi.PrimitiveValue
import java.time.LocalDate
import java.time.format.DateTimeParseException

class ValidatorService(
        private val validatorExecutors: List<ValidatorExecutor<*>> = ValidatorExecutor.DEFAULT_VALIDATOR_EXECUTORS
) {

    fun validateRuleArgumentsValues(rule: Rule, ruleInvocation: RuleInvocation): List<RuleArgumentValidationResult> {
        val results = mutableListOf<RuleArgumentValidationResult>()

        for (ruleArgument in rule.arguments) {
            val ruleInvocationArgument = ruleInvocation.arguments[ruleArgument.name]
            if (ruleInvocationArgument == null) {
                Output.get().addError("Required rule argument \"${ruleArgument.name}\" is missing.")
            } else {
                val result = runValidators(ruleArgument.validators, ruleArgument, ruleInvocationArgument)
                results.add(RuleArgumentValidationResult(ruleArgument, ruleInvocationArgument, result))
            }
        }

        for (ruleInvocationArgument in ruleInvocation.arguments.keys) {
            if (rule.arguments.find { it.name == ruleInvocationArgument } == null) {
                Output.get().addError("Argument with name \"${ruleInvocationArgument}\" is not needed.")
            }
        }

        return results
    }

    private fun runValidators(validators: List<Validator>, argument: MetaArgument, value: MetaValue?): List<ValidationResult> {
        if (value == null) {
            return listOf(ValidationResult.error("Value cannot be null"))
        }
        if (!(value is MetaPrimitiveValue)) {
            return listOf(ValidationResult.error("Value has bad type"))
        }
        if (argument.type.id == "Integer") {
            if (value.value.toIntOrNull() == null) {
                return listOf(ValidationResult.error("Value cannot be parsed as number"))
            }
        } else if (argument.type.id == "LocalDate") {
            try {
                LocalDate.parse(value.value)
            } catch (exception: DateTimeParseException) {
                return listOf(ValidationResult.error("Value cannot be parsed as local date"))
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