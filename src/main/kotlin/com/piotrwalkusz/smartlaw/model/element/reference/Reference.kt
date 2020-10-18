package com.piotrwalkusz.smartlaw.model.element.reference

import com.piotrwalkusz.smartlaw.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.template.Template

data class Reference(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val elementId: Template<Id>
) : Element