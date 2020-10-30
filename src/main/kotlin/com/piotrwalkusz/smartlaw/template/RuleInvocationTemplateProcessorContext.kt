package com.piotrwalkusz.smartlaw.template

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.model.meta.MetaListValue
import com.piotrwalkusz.smartlaw.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.model.rule.Rule
import com.piotrwalkusz.smartlaw.model.rule.RuleInvocation
import freemarker.template.TemplateMethodModel

data class RuleInvocationTemplateProcessorContext(
        val rule: Rule,
        val ruleInvocation: RuleInvocation,
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
        if (rule.arguments.size != ruleInvocation.arguments.size) {
            throw IllegalArgumentException("Count of arguments in rule and rule's invocation must be equal")
        }

        return getTemplateParametersValuesFromRuleInvocationArguments(rule.arguments.zip(ruleInvocation.arguments))
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