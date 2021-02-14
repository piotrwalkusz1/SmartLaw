package com.piotrwalkusz.smartlaw.core.model.element.state

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.ElementTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.TypeTemplate
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class StateTemplate(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>> = StaticTemplate(listOf()),
        val name: Template<String>,
        val description: Template<String?> = StaticTemplate(null),
        val type: Template<TypeTemplate>,
        val defaultValue: Template<MetaValue?> = StaticTemplate(null)
) : ElementTemplate