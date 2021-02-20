package com.piotrwalkusz.smartlaw.core.model.rule

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

data class RuleInvocation(
        val ruleId: Id,
        val arguments: Map<String, MetaValue> = mapOf()
)