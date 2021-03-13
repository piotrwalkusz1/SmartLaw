package com.piotrwalkusz.smartlaw.core.model.element.function.statement

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.VariableRef
import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class EnumValue(
        val enumDefinition: Id,
        val value: String
) : Expression