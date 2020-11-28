package com.piotrwalkusz.smartlaw.core.model.element.common.type

import com.piotrwalkusz.smartlaw.core.model.template.Template

data class GenericType(
        val name: Template<String>
) : Type