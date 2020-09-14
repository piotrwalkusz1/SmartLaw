package com.piotrwalkusz.smartlaw.model.annotation

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.meta.MetaArgument

data class AnnotationType(
        val id: Id,
        val annotations: List<Annotation> = listOf(),
        val name: String,
        val description: String? = null,
        val arguments: List<MetaArgument> = listOf()
)