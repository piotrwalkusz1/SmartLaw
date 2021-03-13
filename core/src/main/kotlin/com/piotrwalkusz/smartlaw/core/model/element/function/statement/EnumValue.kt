package com.piotrwalkusz.smartlaw.core.model.element.function.statement

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.common.Id

@GenerateTemplate
data class EnumValue(
        val enumDefinition: Id,
        val value: String
) : Expression