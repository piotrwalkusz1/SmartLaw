package com.piotrwalkusz.smartlaw.core.model.element.implementation

import com.piotrwalkusz.smartlaw.core.model.element.`interface`.InterfacePropertyType
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Statement
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class PropertyImplementationTemplate(
        val name: Template<String>,
        val type: Template<InterfacePropertyType>,
        val body: Template<Statement>
)