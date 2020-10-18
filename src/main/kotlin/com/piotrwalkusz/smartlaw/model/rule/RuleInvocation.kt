package com.piotrwalkusz.smartlaw.model.rule

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.meta.MetaValue

data class RuleInvocation(
        val ruleId: Id,
        val arguments: List<MetaValue> = listOf()
)