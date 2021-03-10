package com.piotrwalkusz.smartlaw.core.model.element.reference

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.ElementTemplateOld
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class ReferenceTemplate(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val elementId: Template<Id>
) : ElementTemplateOld