package com.piotrwalkusz.smartlaw.core.model.element.common

import com.piotrwalkusz.smartlaw.core.model.element.common.type.TypeTemplate
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class GenericParameterTemplate(
        val name: Template<String>,
        val baseType: Template<TypeTemplate?> = StaticTemplate(null)
)