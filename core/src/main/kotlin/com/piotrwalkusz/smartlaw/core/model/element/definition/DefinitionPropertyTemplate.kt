package com.piotrwalkusz.smartlaw.core.model.element.definition

import com.piotrwalkusz.smartlaw.core.model.element.common.type.TypeTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class DefinitionPropertyTemplate(
        val name: Template<String>,
        val type: Template<TypeTemplate>,
        val description: Template<String?>
)