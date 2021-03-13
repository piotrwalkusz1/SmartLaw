package com.piotrwalkusz.smartlaw.core.model.element.action

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef

@GenerateTemplate
data class ActionArgument(
        val name: String,
        val description: String?,
        val type: DefinitionRef
)