package com.piotrwalkusz.smartlaw.model.element.definition

import com.piotrwalkusz.smartlaw.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.model.template.Template

data class DefinitionProperty(
        val name: Template<String>,
        val type: Template<Type>,
        val description: Template<String?>
)