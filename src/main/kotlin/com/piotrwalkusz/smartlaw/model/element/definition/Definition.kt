package com.piotrwalkusz.smartlaw.model.element.definition

import com.piotrwalkusz.smartlaw.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.model.template.Template

data class Definition(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>> = StaticTemplate(listOf()),
        val name: Template<String>,
        val description: Template<String?> = StaticTemplate(null),
        val parameters: Template<List<GenericParameter>> = StaticTemplate(listOf()),
        val properties: Template<List<DefinitionProperty>> = StaticTemplate(listOf()),
        val baseDefinitions: Template<List<DefinitionRef>> = StaticTemplate(listOf())
) : Element