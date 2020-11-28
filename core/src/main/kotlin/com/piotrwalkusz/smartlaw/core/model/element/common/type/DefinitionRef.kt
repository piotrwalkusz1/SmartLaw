package com.piotrwalkusz.smartlaw.core.model.element.common.type

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class DefinitionRef(
        val definition: Template<Id>,
        val parameters: Template<List<Type>> = StaticTemplate(listOf())
) : Type