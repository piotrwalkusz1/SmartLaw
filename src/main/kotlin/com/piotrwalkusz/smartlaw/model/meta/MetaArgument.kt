package com.piotrwalkusz.smartlaw.model.meta

import com.piotrwalkusz.smartlaw.model.common.Id

data class MetaArgument(
        val name: String,
        val description: String? = null,
        val type: Id
)