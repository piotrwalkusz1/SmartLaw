package com.piotrwalkusz.smartlaw.core.model.element.definition

import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class DefinitionProperty(
        val name: Template<String>,
        val type: Template<Type>,
        val description: Template<String?>
)