package com.piotrwalkusz.smartlaw.model.element.common.type

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.model.template.Template

data class DefinitionRef(
        val definition: Template<Id>,
        val parameters: Template<List<Type>> = StaticTemplate(listOf())
) : Type