package com.piotrwalkusz.smartlaw.compiler.validator.executor.evaluator

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import freemarker.template.Configuration
import freemarker.template.Template
import java.io.StringReader
import java.io.StringWriter

class FreeMarkerExpressionEvaluator : ExpressionEvaluator {

    companion object {
        const val EXPRESSION_ENGINE_TYPE = "FreeMarker"
    }

    override val expressionEvaluatorType: String
        get() = EXPRESSION_ENGINE_TYPE

    private val configuration: Configuration = Configuration()

    override fun evaluate(expression: String, parameters: Map<String, Any?>): String? {
        val output = StringWriter()
        val freeMarkerTemplate = Template(null, StringReader(expression), configuration)
        // TODO: Handler is not working
        freeMarkerTemplate.setTemplateExceptionHandler { te, env, out -> Output.get().addError("Error in validator expression") }
        freeMarkerTemplate.process(parameters, output)

        return if (output.toString() == "") null else output.toString()
    }
}