package com.piotrwalkusz.smartlaw.template

import com.piotrwalkusz.smartlaw.model.common.Id
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
        val parametersKeys = rule.arguments.map { it.name }
        val parametersValues = getTemplateParametersValuesFromRuleInvocationArguments(ruleInvocation)
        if (parametersKeys.size != parametersValues.size) {
            throw IllegalArgumentException("Count of arguments in rule and rule's invocation must be equal")
        }

        return parametersKeys.zip(parametersValues).toMap()
    }

    private fun getTemplateParametersValuesFromRuleInvocationArguments(ruleInvocation: RuleInvocation): List<Any> {
        return ruleInvocation.arguments.map { getTemplateParameterValueFromRuleInvocationArgument(it) }
    }

    private fun getTemplateParameterValueFromRuleInvocationArgument(argument: MetaValue): Any {
        if (argument is MetaPrimitiveValue) {
            return argument.value
        } else if (argument is MetaListValue) {
            return argument.values
        } else {
            throw IllegalArgumentException("Cannot convert class ${argument::class.java} to template parameter value")
        }
    }
}