package com.piotrwalkusz.smartlaw.model.element.annotation

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element

data class AnnotationType(
        override val id: Id,
        override val annotations: List<Annotation> = listOf(),
        val name: String,
        val description: String? = null,
        val properties: List<AnnotationProperty> = listOf()
) : Element