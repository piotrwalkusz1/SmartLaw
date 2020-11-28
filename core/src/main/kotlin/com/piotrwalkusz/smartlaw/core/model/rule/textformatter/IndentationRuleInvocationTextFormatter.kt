package com.piotrwalkusz.smartlaw.core.model.rule.textformatter

data class IndentationRuleInvocationTextFormatter(
        val ruleInvocations: List<RuleInvocationTextFormatter> = listOf()
) : RuleInvocationTextFormatter