package com.piotrwalkusz.smartlaw.template

import com.piotrwalkusz.smartlaw.model.meta.MetaComplexValue
import com.piotrwalkusz.smartlaw.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.model.template.TextEngineTemplate

class TextEngineTemplateProcessor(private val textEngines: List<TextEngine>) : TemplateProcessor<TextEngineTemplate, String> {

    override val templateType: Class<TextEngineTemplate>
        get() = TextEngineTemplate::class.java

    override fun processTemplate(template: TextEngineTemplate, context: TemplateProcessorContext): String {
        val parameters = getTemplateParameters(context);

        return getTextEngine(template).processTemplate(template.template, parameters)
    }

    private fun getTextEngine(template: TextEngineTemplate): TextEngine {
        return textEngines.find { textEngine -> textEngine.templateType == template.type }
                ?: throw IllegalArgumentException("Cannot find text engine to template with type ${template.type}")
    }

    private fun getTemplateParameters(context: TemplateProcessorContext): Map<String, Any> {
        val parametersKeys = context.rule.arguments.map { it.name }
        val parametersValues = getTemplateParametersValues(context.ruleInvocation)
        if (parametersKeys.size != parametersValues.size) {
            throw IllegalArgumentException("Count of arguments in rule and rule's invocation must be equal")
        }

        return parametersKeys.zip(parametersValues).toMap()
    }

    private fun getTemplateParametersValues(ruleInvocation: RuleInvocation): List<Any> {
        return ruleInvocation.arguments.map { getTemplateParameterValue(it) }
    }

    private fun getTemplateParameterValue(value: MetaValue): Any {
        if (value is MetaPrimitiveValue) {
            return value.value
        } else if (value is MetaComplexValue) {
            return value.values
        } else {
            throw IllegalArgumentException("Cannot convert class ${value::class.java} to template parameter value")
        }
    }
}