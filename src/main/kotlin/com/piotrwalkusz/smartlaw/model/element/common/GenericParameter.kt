package com.piotrwalkusz.smartlaw.model.element.common

import com.piotrwalkusz.smartlaw.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.model.template.Template

data class GenericParameter(
        val name: Template<String>,
        val baseType: Template<Type?> = StaticTemplate(null)
)