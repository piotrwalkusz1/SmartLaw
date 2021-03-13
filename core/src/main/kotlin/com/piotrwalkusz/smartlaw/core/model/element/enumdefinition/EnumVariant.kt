package com.piotrwalkusz.smartlaw.core.model.element.enumdefinition

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate

@GenerateTemplate
data class EnumVariant(
        val name: String,
        val description: String?
)