package com.piotrwalkusz.smartlaw.compiler.validator.executor.evaluator

interface ExpressionEvaluator {

    companion object {
        val DEFAULT_EXPRESSION_EVALUATORS = listOf<ExpressionEvaluator>(FreeMarkerExpressionEvaluator())
    }

    val expressionEvaluatorType: String

    fun evaluate(expression: String, parameters: Map<String, Any?>): String?
}