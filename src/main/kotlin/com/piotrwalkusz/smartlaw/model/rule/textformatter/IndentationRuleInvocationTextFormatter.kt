package com.piotrwalkusz.smartlaw.model.rule.textformatter

data class IndentationRuleInvocationTextFormatter(
        val ruleInvocations: List<RuleInvocationTextFormatter> = listOf()
) : RuleInvocationTextFormatter