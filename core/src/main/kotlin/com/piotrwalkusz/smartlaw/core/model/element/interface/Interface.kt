package com.piotrwalkusz.smartlaw.core.model.element.`interface`

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.GenericParameterTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.InterfaceRefTemplate

data class Interface(
        override val id: Id,
        override val annotations: List<Annotation>,
        val name: String,
        val description: String?,
        val parameters: List<GenericParameterTemplate>,
        val properties: List<InterfaceProperty>,
        val baseDefinitions: List<InterfaceRefTemplate>
) : Element