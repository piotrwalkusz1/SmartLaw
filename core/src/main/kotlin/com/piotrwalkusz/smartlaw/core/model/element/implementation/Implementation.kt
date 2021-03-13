package com.piotrwalkusz.smartlaw.core.model.element.implementation

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.element.common.type.InterfaceRef

@GenerateTemplate
data class Implementation(
        override val id: Id,
        override val annotations: List<Annotation>,
        val interfaceId: InterfaceRef,
        val definitionId: DefinitionRef,
        val properties: List<PropertyImplementation>
) : Element