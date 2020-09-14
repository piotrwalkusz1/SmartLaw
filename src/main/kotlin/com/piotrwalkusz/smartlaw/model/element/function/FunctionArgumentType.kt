package com.piotrwalkusz.smartlaw.model.element.function

import com.piotrwalkusz.smartlaw.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.model.template.Template

data class FunctionArgumentType(
        val name: Template<String>,
        val description: Template<String?>,
        val type: Template<Type>
)