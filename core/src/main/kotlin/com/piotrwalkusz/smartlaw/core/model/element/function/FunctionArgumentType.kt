package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.core.model.element.common.type.TypeTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class FunctionArgumentType(
        val name: Template<String>,
        val description: Template<String?>,
        val type: Template<TypeTemplate>
)