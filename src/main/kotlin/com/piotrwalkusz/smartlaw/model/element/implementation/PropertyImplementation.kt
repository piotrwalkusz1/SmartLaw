package com.piotrwalkusz.smartlaw.model.element.implementation

import com.piotrwalkusz.smartlaw.model.element.`interface`.InterfacePropertyType
import com.piotrwalkusz.smartlaw.model.element.function.statement.Statement
import com.piotrwalkusz.smartlaw.model.template.Template

data class PropertyImplementation(
        val name: Template<String>,
        val type: Template<InterfacePropertyType>,
        val body: Template<Statement>
)