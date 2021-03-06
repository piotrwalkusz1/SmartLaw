package com.piotrwalkusz.smartlaw.core.model.element.definition

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type

@GenerateTemplate
data class DefinitionProperty(
        val name: String,
        val type: Type,
        val description: String?
)