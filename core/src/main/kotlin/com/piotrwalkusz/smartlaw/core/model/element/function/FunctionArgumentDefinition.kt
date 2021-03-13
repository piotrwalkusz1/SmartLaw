package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type

@GenerateTemplate
data class FunctionArgumentDefinition(
        val name: String,
        val description: String?,
        val type: Type
)