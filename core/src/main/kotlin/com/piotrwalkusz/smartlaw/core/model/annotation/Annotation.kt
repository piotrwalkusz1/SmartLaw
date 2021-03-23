package com.piotrwalkusz.smartlaw.core.model.annotation

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

@GenerateTemplate
data class Annotation(
        val annotationType: Id,
        val arguments: List<MetaValue> = listOf()
)