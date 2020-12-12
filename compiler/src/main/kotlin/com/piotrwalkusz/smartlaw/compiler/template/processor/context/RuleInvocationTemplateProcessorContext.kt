package com.piotrwalkusz.smartlaw.compiler.template.processor.context

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.validator.RuleArgumentValidationResult
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.meta.MetaListValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import freemarker.template.TemplateMethodModel
import pl.allegro.finance.tradukisto.MoneyConverters
import pl.allegro.finance.tradukisto.ValueConverters
import pl.allegro.finance.tradukisto.internal.converters.IntegerToWordsConverter
import java.math.BigDecimal


data class RuleInvocationTemplateProcessorContext(
        val ruleArgumentValidationResults: List<RuleArgumentValidationResult>,
        val linksByElementsIds: Map<Id, String>
) : TemplateProcessorContext {

    override fun getTemplateParameters(): Map<String, Any> {
        return mapOf("args" to getTemplateParametersFromRuleInvocationArguments(),
                "context" to mapOf("getLinkToElement" to TemplateMethodModel { args -> getLinkToElement(args[0] as String, linksByElementsIds) },
                        "getNumberInWords" to TemplateMethodModel { args -> getNumberInWords(args[0]) }))
    }

    private fun getLinkToElement(elementId: String, linksByElementsIds: Map<Id, String>): String? {
        val linkToElement = linksByElementsIds[convertStringToId(elementId)]
        if (linkToElement == null) {
            Output.get().addError("Element with id \"$elementId\" does not exist")
        }

        return linkToElement
    }

    private fun getNumberInWords(value: Any?): String {
        if (value !is String) {
            return ""
        }
        val number = value.toIntOrNull() ?: return ""
        val converter = ValueConverters.POLISH_INTEGER

        return converter.asWords(number) + " PLN"
    }

    private fun convertStringToId(idAsString: String): Id {
        return Id(idAsString.substringAfterLast("."), idAsString.substringBeforeLast("."))
    }

    private fun getTemplateParametersFromRuleInvocationArguments(): Map<String, Any> {
        val arguments = ruleArgumentValidationResults.mapNotNull { if (it.value == null) null else it.argument to it.value }

        return getTemplateParametersValuesFromRuleInvocationArguments(arguments)
    }

    private fun getTemplateParametersValuesFromRuleInvocationArguments(arguments: List<Pair<MetaArgument, MetaValue>>): Map<String, Any> {
        return arguments.map { it.first.name to getTemplateParameterValueFromRuleInvocationArgument(it.first, it.second) }.toMap()
    }

    private fun getTemplateParameterValueFromRuleInvocationArgument(argumentDefinition: MetaArgument, argumentValue: MetaValue): Any {
        return when (argumentValue) {
            is MetaPrimitiveValue -> {
                convertPrimitiveArgumentValue(argumentDefinition, argumentValue)
            }
            is MetaListValue -> {
                argumentValue.values
            }
            else -> {
                throw IllegalArgumentException("Cannot convert class ${argumentValue::class.java} to template parameter value")
            }
        }
    }

    private fun convertPrimitiveArgumentValue(argumentDefinition: MetaArgument, argumentValue: MetaPrimitiveValue): Any {
        return when (argumentDefinition.type.id) {
            "String" -> {
                argumentValue.value
            }
            "Integer" -> {
                val number = argumentValue.value.toIntOrNull()
                if (number == null) {
                    Output.get().addError("Cannot convert \"${argumentValue.value}\" to number")
                }
                number ?: ""
            }
            "LocalDate" -> {
                argumentValue.value
            }
            else -> {
                throw IllegalArgumentException("Type of argument ${argumentDefinition.type.id} is not recognized")
            }
        }
    }
}