package com.piotrwalkusz.smartlaw.core.model.element.`interface`

import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class InterfaceProperty(
        val name: Template<String>,
        val propertyType: Template<InterfacePropertyType>,
        val type: Template<Type>,
        val description: Template<String?> = StaticTemplate(null)
)