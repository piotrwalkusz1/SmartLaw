package com.piotrwalkusz.smartlaw.model.element.common.type

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.template.Template

data class InterfaceRef(
        val interfaceId: Template<Id>,
        val parameters: Template<List<Type>>
) : Type