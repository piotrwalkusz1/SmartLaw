package com.piotrwalkusz.smartlaw.core.model.element.common.type

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.common.Id

@GenerateTemplate
data class InterfaceRef(
        val interfaceId: Id,
        val parameters: List<Type>
) : Type