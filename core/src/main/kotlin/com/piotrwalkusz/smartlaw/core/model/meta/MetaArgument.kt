package com.piotrwalkusz.smartlaw.core.model.meta

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.validator.Validator

data class MetaArgument(
        val name: String,
        val displayName: String? = null,
        val description: String? = null,
        val type: Id,
        val validators: List<Validator> = listOf()
)