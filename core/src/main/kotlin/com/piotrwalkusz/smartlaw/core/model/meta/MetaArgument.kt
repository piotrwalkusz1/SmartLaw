package com.piotrwalkusz.smartlaw.core.model.meta

import com.piotrwalkusz.smartlaw.core.model.common.Id

data class MetaArgument(
        val name: String,
        val description: String? = null,
        val type: Id
)