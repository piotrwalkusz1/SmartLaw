package com.piotrwalkusz.smartlaw.model.element.state

import com.piotrwalkusz.smartlaw.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.model.template.Template

data class State(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>> = StaticTemplate(listOf()),
        val name: Template<String>,
        val description: Template<String?> = StaticTemplate(null),
        val type: Template<Type>,
        val defaultValue: Template<MetaValue?> = StaticTemplate(null)
) : Element