package com.piotrwalkusz.smartlaw.core.model.element.implementation

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Statement
import com.piotrwalkusz.smartlaw.core.model.element.interfaces.InterfacePropertyType

@GenerateTemplate
data class PropertyImplementation(
        val name: String,
        val type: InterfacePropertyType,
        val body: Statement
)