package com.piotrwalkusz.smartlaw.core.model.element.function.argument

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.common.Id

@GenerateTemplate
data class StateVariableRef(
        val state: Id
) : VariableRef