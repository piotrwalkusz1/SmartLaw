package com.piotrwalkusz.smartlaw.compiler.rule.model

import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

data class RuleArgumentWithValue(
        val argument: MetaArgument,
        val value: MetaValue
)