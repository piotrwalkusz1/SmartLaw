package com.piotrwalkusz.smartlaw.model.element.`interface`

import com.piotrwalkusz.smartlaw.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.model.element.common.type.InterfaceRef
import com.piotrwalkusz.smartlaw.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.model.template.Template

data class Interface(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>> = StaticTemplate(listOf()),
        val name: Template<String>,
        val description: Template<String?> = StaticTemplate(null),
        val parameters: Template<List<GenericParameter>> = StaticTemplate(listOf()),
        val properties: Template<List<InterfaceProperty>> = StaticTemplate(listOf()),
        val baseDefinitions: Template<List<InterfaceRef>> = StaticTemplate(listOf())
) : Element