package com.piotrwalkusz.smartlaw.core.model.element.interfaces

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type

@GenerateTemplate
data class InterfaceProperty(
        val name: String,
        val propertyType: InterfacePropertyType,
        val type: Type,
        val description: String?
)