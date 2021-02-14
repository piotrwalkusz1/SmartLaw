package com.piotrwalkusz.smartlaw.core.model.element.common.type

import com.piotrwalkusz.smartlaw.core.model.common.Id

data class InterfaceRef(
        val interfaceId: Id,
        val parameters: List<Type>
) : Type