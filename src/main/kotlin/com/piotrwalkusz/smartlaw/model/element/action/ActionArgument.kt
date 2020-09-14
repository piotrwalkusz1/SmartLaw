package com.piotrwalkusz.smartlaw.model.element.action

import com.piotrwalkusz.smartlaw.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.model.template.Template

data class ActionArgument(
        val name: Template<String>,
        val description: Template<String?>,
        val type: Template<DefinitionRef>
)