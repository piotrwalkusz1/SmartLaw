package com.piotrwalkusz.smartlaw.core.model.element.interfaces

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.core.model.element.common.type.InterfaceRef

@GenerateTemplate
data class Interface(
        override val id: Id,
        override val annotations: List<Annotation>,
        val name: String,
        val description: String?,
        val parameters: List<GenericParameter>,
        val properties: List<InterfaceProperty>,
        val baseDefinitions: List<InterfaceRef>
) : Element