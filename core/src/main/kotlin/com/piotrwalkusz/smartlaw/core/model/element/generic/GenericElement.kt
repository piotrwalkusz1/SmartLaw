package com.piotrwalkusz.smartlaw.core.model.element.generic

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element

data class GenericElement(
        override val id: Id,
        override val annotations: List<Annotation>,
        val content: String
) : Element