package com.piotrwalkusz.smartlaw.core.model.element.implementation

import com.piotrwalkusz.smartlaw.core.model.element.`interface`.InterfacePropertyType
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Statement

data class PropertyImplementation(
        val name: String,
        val type: InterfacePropertyType,
        val body: Statement
)