package com.piotrwalkusz.smartlaw.compiler.validator.executor

import com.piotrwalkusz.smartlaw.compiler.validator.ValidationResult
import com.piotrwalkusz.smartlaw.compiler.validator.executor.evaluator.ExpressionEvaluator
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.validator.GenericValidator
import com.sun.jdi.PrimitiveValue

class GenericValidatorExecutor(
        private val expressionEvaluators: List<ExpressionEvaluator> = ExpressionEvaluator.DEFAULT_EXPRESSION_EVALUATORS
) : ValidatorExecutor<GenericValidator> {

    override val validatorType: Class<GenericValidator>
        get() = GenericValidator::class.java

    override fun validate(validator: GenericValidator, value: MetaValue): ValidationResult {
        val parameters = mapOf("value" to convertMetaValueToExpressionEvaluatorParameter(value))
        val expressionEvaluator = getExpressionEvaluator(validator.expressionEvaluatorType)
                ?: return ValidationResult.error("No expression evaluator with type ${validator.expressionEvaluatorType}")

        return ValidationResult(expressionEvaluator.evaluate(validator.expression, parameters))
    }

    private fun convertMetaValueToExpressionEvaluatorParameter(value: MetaValue): Any? {
        return when (value) {
            is MetaPrimitiveValue -> value.value
            else -> null
        }
    }

    private fun getExpressionEvaluator(expressionEvaluatorType: String): ExpressionEvaluator? {
        return expressionEvaluators.find { it.expressionEvaluatorType == expressionEvaluatorType }
    }
}