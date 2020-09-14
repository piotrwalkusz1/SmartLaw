package com.piotrwalkusz.smartlaw.model.element.action

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.template.Template

data class ActionDefinition(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val name: Template<String>,
        val description: Template<String?>,
        val arguments: Template<List<ActionArgument>>
) : Element