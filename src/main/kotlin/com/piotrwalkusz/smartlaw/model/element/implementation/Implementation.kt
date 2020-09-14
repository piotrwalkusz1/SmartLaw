package com.piotrwalkusz.smartlaw.model.element.implementation

import com.piotrwalkusz.smartlaw.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.model.element.common.type.InterfaceRef
import com.piotrwalkusz.smartlaw.model.template.Template

data class Implementation(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val interfaceId: Template<InterfaceRef>,
        val definitionId: Template<DefinitionRef>,
        val properties: Template<List<PropertyImplementation>>
) : Element