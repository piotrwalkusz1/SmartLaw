package com.piotrwalkusz.smartlaw.model.element.annotation

import com.piotrwalkusz.smartlaw.model.common.Id

data class AnnotationProperty(
        val name: String,
        val description: String? = null,
        val type: Id
)