package com.piotrwalkusz.smartlaw.core.model.meta

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation

@GenerateTemplate
data class MetaRuleValue(
        val ruleInvocation: RuleInvocation
) : MetaValue