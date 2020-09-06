package com.piotrwalkusz.smartlaw.model.element.common

import com.piotrwalkusz.smartlaw.model.element.common.type.Type

data class GenericParameter(
        val name: String,
        val baseType: Type
)