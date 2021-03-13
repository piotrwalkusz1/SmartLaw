package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.common.type.TypeTemplate

data class FunctionRef(
        val id: Id,
        val parameters: List<TypeTemplate>
)