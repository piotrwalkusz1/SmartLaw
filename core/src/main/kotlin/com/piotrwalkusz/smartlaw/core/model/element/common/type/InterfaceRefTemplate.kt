package com.piotrwalkusz.smartlaw.core.model.element.common.type

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class InterfaceRefTemplate(
        val interfaceId: Template<Id>,
        val parameters: Template<List<TypeTemplate>>
) : TypeTemplate