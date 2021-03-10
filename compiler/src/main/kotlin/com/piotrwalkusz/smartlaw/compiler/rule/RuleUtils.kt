package com.piotrwalkusz.smartlaw.compiler.rule

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.rule.model.RuleArgumentWithValue
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation

class RuleUtils {

    companion object {

        fun associateRuleArgumentsWithValues(rule: Rule, ruleInvocation: RuleInvocation): List<RuleArgumentWithValue> {
            val results = mutableListOf<RuleArgumentWithValue>()

            for (ruleArgument in rule.arguments) {
                val ruleInvocationArgument = ruleInvocation.arguments[ruleArgument.name]
                if (ruleInvocationArgument == null) {
                    Output.get().addError("Required rule argument \"${ruleArgument.name}\" is missing.")
                } else {
                    results.add(RuleArgumentWithValue(ruleArgument, ruleInvocationArgument))
                }
            }

            for (ruleInvocationArgument in ruleInvocation.arguments.keys) {
                if (rule.arguments.find { it.name == ruleInvocationArgument } == null) {
                    Output.get().addError("Argument with name \"${ruleInvocationArgument}\" is not needed.")
                }
            }

            return results
        }
    }
}