package com.piotrwalkusz.smartlaw.core.model.element.common

import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class GenericParameter(
        val name: Template<String>,
        val baseType: Template<Type?> = StaticTemplate(null)
)