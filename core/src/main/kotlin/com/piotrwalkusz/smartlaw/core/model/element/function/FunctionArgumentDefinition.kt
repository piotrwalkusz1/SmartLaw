package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.element.common.type.TypeTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class FunctionArgumentDefinition(
        val name: String,
        val description: String?,
        val type: Type
)