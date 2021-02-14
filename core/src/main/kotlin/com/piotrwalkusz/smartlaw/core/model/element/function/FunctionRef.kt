package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.common.type.TypeTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class FunctionRef(
        val id: Template<Id>,
        val parameters: Template<List<TypeTemplate>>
)