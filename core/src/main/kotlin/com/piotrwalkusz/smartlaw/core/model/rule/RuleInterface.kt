package com.piotrwalkusz.smartlaw.core.model.rule

import com.piotrwalkusz.smartlaw.core.model.common.Id

data class RuleInterface(
        val id: Id,
        val name: String,
        val description: String? = null
)