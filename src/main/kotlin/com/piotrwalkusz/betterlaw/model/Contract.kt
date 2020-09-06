package com.piotrwalkusz.betterlaw.model

import com.piotrwalkusz.betterlaw.model.common.Id

data class Contract(
        val id: Id,
        val name: String,
        val description: String? = null
//        val ruleInvocations: List<RuleInvocation> = listOf()
)