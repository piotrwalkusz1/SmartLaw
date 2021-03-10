package com.piotrwalkusz.smartlaw.core.model.element.definition

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.ElementTemplateOld
import com.piotrwalkusz.smartlaw.core.model.element.common.GenericParameterTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRefTemplateOld
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class DefinitionTemplate(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>> = StaticTemplate(listOf()),
        val name: Template<String>,
        val description: Template<String?> = StaticTemplate(null),
        val parameters: Template<List<GenericParameterTemplate>> = StaticTemplate(listOf()),
        val properties: Template<List<DefinitionPropertyTemplate>> = StaticTemplate(listOf()),
        val baseDefinitions: Template<List<DefinitionRefTemplateOld>> = StaticTemplate(listOf())
) : ElementTemplateOld