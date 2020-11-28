package com.piotrwalkusz.smartlaw.core.model.element.`interface`

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.core.model.element.common.type.InterfaceRef
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class Interface(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>> = StaticTemplate(listOf()),
        val name: Template<String>,
        val description: Template<String?> = StaticTemplate(null),
        val parameters: Template<List<GenericParameter>> = StaticTemplate(listOf()),
        val properties: Template<List<InterfaceProperty>> = StaticTemplate(listOf()),
        val baseDefinitions: Template<List<InterfaceRef>> = StaticTemplate(listOf())
) : Element