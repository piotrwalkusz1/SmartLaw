package com.piotrwalkusz.smartlaw.core.model.element.`interface`

import com.piotrwalkusz.smartlaw.core.model.element.common.type.TypeTemplate
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class InterfaceProperty(
        val name: Template<String>,
        val propertyType: Template<InterfacePropertyType>,
        val type: Template<TypeTemplate>,
        val description: Template<String?> = StaticTemplate(null)
)