package com.piotrwalkusz.smartlaw.model

import com.piotrwalkusz.smartlaw.model.common.Id

data class Contract(
        val id: Id,
        val name: String,
        val description: String? = null
//        val ruleInvocations: List<RuleInvocation> = listOf()
)