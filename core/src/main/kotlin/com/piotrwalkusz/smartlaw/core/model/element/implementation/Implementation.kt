package com.piotrwalkusz.smartlaw.core.model.element.implementation

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRefTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.InterfaceRefTemplate

data class Implementation(
        override val id: Id,
        override val annotations: List<Annotation>,
        val interfaceId: InterfaceRefTemplate,
        val definitionId: DefinitionRefTemplate,
        val properties: List<PropertyImplementation>
) : Element