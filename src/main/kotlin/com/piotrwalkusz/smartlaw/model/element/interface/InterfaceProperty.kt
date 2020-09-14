package com.piotrwalkusz.smartlaw.model.element.`interface`

import com.piotrwalkusz.smartlaw.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.model.template.Template

data class InterfaceProperty(
        val name: Template<String>,
        val propertyType: Template<InterfacePropertyType>,
        val type: Template<Type>,
        val description: Template<String?> = StaticTemplate(null)
)