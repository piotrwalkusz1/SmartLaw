package com.piotrwalkusz.smartlaw.core.model.meta

import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation

data class MetaRuleValue(
        val ruleInvocation: RuleInvocation
) : MetaValue