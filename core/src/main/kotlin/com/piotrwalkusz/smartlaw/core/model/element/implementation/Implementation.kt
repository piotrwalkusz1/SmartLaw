package com.piotrwalkusz.smartlaw.core.model.element.implementation

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.element.common.type.InterfaceRef
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class Implementation(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val interfaceId: Template<InterfaceRef>,
        val definitionId: Template<DefinitionRef>,
        val properties: Template<List<PropertyImplementation>>
) : Element