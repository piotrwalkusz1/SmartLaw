package com.piotrwalkusz.betterlaw.model.element.annotation

import com.piotrwalkusz.betterlaw.model.common.Id

data class AnnotationProperty(
        val name: String,
        val description: String? = null,
        val type: Id
)