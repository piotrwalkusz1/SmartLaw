package com.piotrwalkusz.smartlaw.core.model.element.reference

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element

@GenerateTemplate
data class Reference(
        override val id: Id,
        override val annotations: List<Annotation>,
        val elementId: Id
) : Element