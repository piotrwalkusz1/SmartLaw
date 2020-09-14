package com.piotrwalkusz.smartlaw.model.element.common.type

import com.piotrwalkusz.smartlaw.model.template.Template

data class GenericType(
        val name: Template<String>
) : Type