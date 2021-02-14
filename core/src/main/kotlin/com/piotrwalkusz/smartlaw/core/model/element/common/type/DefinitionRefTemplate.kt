package com.piotrwalkusz.smartlaw.core.model.element.common.type

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class DefinitionRefTemplate(
        val definition: Template<Id>,
        val parameters: Template<List<TypeTemplate>> = StaticTemplate(listOf())
) : TypeTemplate