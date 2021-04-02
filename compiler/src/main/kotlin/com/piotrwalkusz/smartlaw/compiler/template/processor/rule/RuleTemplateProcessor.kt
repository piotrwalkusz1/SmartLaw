package com.piotrwalkusz.smartlaw.compiler.template.processor.rule

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.meta.MetaTypeService
import com.piotrwalkusz.smartlaw.compiler.rule.model.RuleArgumentWithValue
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.model.ProcessRuleTemplateConfig
import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.meta.MetaRuleValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.template.Template
import freemarker.template.TemplateMethodModel
import pl.allegro.finance.tradukisto.ValueConverters

abstract class RuleTemplateProcessor<T, C : ProcessRuleTemplateConfig>(
        private val templateProcessorService: TemplateProcessorService
) {

    fun processTemplate(template: Template<T>, ruleArguments: List<RuleArgumentWithValue>, config: C): T {
        val context = getTemplateProcessorContext(ruleArguments, config)
        val modifiedTemplate = modifyTemplate(template, config)
        return templateProcessorService.processTemplate(modifiedTemplate, context)
    }

    protected open fun modifyTemplate(template: Template<T>, config: C): Template<T> {
        return template
    }

    protected open fun getAdditionalContextParameters(config: C): Map<String, Any?> {
        return emptyMap()
    }

    protected open fun getAdditionalParametersFromMetaRuleValue(argumentValue: MetaRuleValue, config: C): Map<String, Any?> {
        return emptyMap()
    }

    private fun getTemplateProcessorContext(ruleArguments: List<RuleArgumentWithValue>, config: C): TemplateProcessorContext {
        return mapOf(
                "args" to getTemplateParametersFromRuleArguments(ruleArguments, config),
                "context" to getContextParameters(config)
        )
                .let { TemplateProcessorContext(it) }
    }

    private fun getContextParameters(config: C): Map<String, Any?> {
        val parameters = mapOf("getNumberInWords" to TemplateMethodModel { args -> getNumberInWords(args[0]) })
        val additionalParameters = getAdditionalContextParameters(config)

        return parameters + additionalParameters
    }

    private fun getNumberInWords(value: Any?): String {
        if (value !is String) {
            return ""
        }
        val number = value.toIntOrNull() ?: return ""
        val converter = ValueConverters.POLISH_INTEGER

        return converter.asWords(number)
    }

    private fun getTemplateParametersFromRuleArguments(ruleArguments: List<RuleArgumentWithValue>, config: C): Map<String, Any?> {
        return ruleArguments
                .map { (argument, value) -> argument.name to getTemplateParameterValueFromRuleArgument(argument, value, config) }
                .toMap()
    }

    private fun getTemplateParameterValueFromRuleArgument(argumentDefinition: MetaArgument, argumentValue: MetaValue, config: C): Any? {
        val metaType = MetaTypeService.DEFAULT_META_TYPES.find { it.getId() == argumentDefinition.type }
        return if (metaType == null) {
            if (argumentValue is MetaRuleValue) {
                getAdditionalParametersFromMetaRuleValue(argumentValue, config)
            } else {
                Output.get().addError("Expected MetaRuleValue")
                null
            }
        } else {
            metaType.convertMetaValueToTemplateParameter(argumentValue)
        }
    }
}