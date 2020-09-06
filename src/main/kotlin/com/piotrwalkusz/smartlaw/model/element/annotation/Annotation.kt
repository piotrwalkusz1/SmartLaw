package com.piotrwalkusz.smartlaw.model.element.annotation

import com.piotrwalkusz.smartlaw.model.common.Id

data class Annotation(
        val annotationType: Id,
        val properties: List<AnnotationValue> = listOf()
)