package com.piotrwalkusz.smartlaw.compiler.template.processor.context

import com.piotrwalkusz.smartlaw.compiler.common.Output
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.meta.MetaListValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import freemarker.template.TemplateMethodModel

data class RuleInvocationTemplateProcessorContext(
        val rule: Rule,
        val arguments: List<MetaValue>,
        val linksByElementsIds: Map<Id, String>
) : TemplateProcessorContext {

    override fun getTemplateParameters(): Map<String, Any> {
        return mapOf("args" to getTemplateParametersFromRuleInvocationArguments(),
                "context" to mapOf("getLinkToElement" to TemplateMethodModel { args -> linksByElementsIds[convertStringToId(args[0] as String)]!! }))
    }

    private fun convertStringToId(idAsString: String): Id {
        return Id(idAsString.substringAfterLast("."), idAsString.substringBeforeLast("."))
    }

    private fun getTemplateParametersFromRuleInvocationArguments(): Map<String, Any> {
        if (rule.arguments.size != arguments.size) {
            Output.get().addError("Count of arguments in rule and rule's invocation must be equal. Rule has" +
                    "${rule.arguments.size} arguments rule invocation has ${arguments.size} arguments.")
        }

        return getTemplateParametersValuesFromRuleInvocationArguments(rule.arguments.zip(arguments))
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
                argumentValue.value.toInt()
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