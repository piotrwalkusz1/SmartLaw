package com.piotrwalkusz.smartlaw.template

import com.piotrwalkusz.smartlaw.model.rule.Rule
import com.piotrwalkusz.smartlaw.model.rule.RuleInvocation

data class TemplateProcessorContext(
        val rule: Rule,
        val ruleInvocation: RuleInvocation
)