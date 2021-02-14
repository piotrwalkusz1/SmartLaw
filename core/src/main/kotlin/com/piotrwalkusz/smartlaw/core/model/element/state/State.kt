package com.piotrwalkusz.smartlaw.core.model.element.state

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

data class State(
        override val id: Id,
        override val annotations: List<Annotation> = listOf(),
        val name: String,
        val description: String? = null,
        val type: Type,
        val defaultValue: MetaValue? = null
) : Element