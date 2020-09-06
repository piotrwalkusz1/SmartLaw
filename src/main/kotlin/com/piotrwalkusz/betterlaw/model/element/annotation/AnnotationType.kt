package com.piotrwalkusz.betterlaw.model.element.annotation

import com.piotrwalkusz.betterlaw.model.common.Id
import com.piotrwalkusz.betterlaw.model.element.Element

data class AnnotationType(
        override val id: Id,
        override val annotations: List<Annotation> = listOf(),
        val name: String,
        val description: String? = null,
        val properties: List<AnnotationProperty> = listOf()
) : Element