package com.piotrwalkusz.smartlaw.core.model.element.common.type

import com.piotrwalkusz.smartlaw.core.model.template.Template

data class GenericTypeTemplate(
        val name: Template<String>
) : TypeTemplate