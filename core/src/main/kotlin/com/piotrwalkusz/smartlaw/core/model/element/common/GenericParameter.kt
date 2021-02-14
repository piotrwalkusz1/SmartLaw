package com.piotrwalkusz.smartlaw.core.model.element.common

import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type

data class GenericParameter(
        val name: String,
        val baseType: Type?
)