package com.piotrwalkusz.smartlaw.core.model.element.common

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type

@GenerateTemplate
data class GenericParameter(
        val name: String,
        val baseType: Type?
)