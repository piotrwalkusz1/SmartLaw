package com.piotrwalkusz.betterlaw.model

import com.piotrwalkusz.betterlaw.model.common.Id

data class Library(
        val id: Id,
        val name: String,
        val description: String? = null
//        var rules: List<Rule> = listOf(),
//        var ruleInvocations: List<RuleInvocation> = listOf()
)