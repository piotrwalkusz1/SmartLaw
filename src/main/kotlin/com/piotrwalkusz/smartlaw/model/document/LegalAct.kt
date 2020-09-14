package com.piotrwalkusz.smartlaw.model.document

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.rule.RuleInvocation

data class LegalAct(
        val id: Id,
        val name: String,
        val description: String? = null,
        val ruleInvocations: List<RuleInvocation>
) : Document