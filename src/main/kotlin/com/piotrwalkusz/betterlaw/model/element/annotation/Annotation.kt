package com.piotrwalkusz.betterlaw.model.element.annotation

import com.piotrwalkusz.betterlaw.model.common.Id

data class Annotation(
        val annotationType: Id,
        val properties: List<AnnotationValue> = listOf()
)