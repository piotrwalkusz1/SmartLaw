package com.piotrwalkusz.smartlaw.model.element.function

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.model.template.Template

data class FunctionRef(
        val id: Template<Id>,
        val parameters: Template<List<Type>>
)