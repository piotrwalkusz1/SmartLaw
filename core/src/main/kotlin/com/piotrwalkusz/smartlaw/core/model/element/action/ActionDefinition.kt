package com.piotrwalkusz.smartlaw.core.model.element.action

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.function.FunctionRef

@GenerateTemplate
data class ActionDefinition(
        override val id: Id,
        override val annotations: List<Annotation>,
        val name: String,
        val description: String?,
        val arguments: List<ActionArgument>,
        val function: FunctionRef
) : Element