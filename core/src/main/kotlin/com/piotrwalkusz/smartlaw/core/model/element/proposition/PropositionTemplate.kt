package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.ElementTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class PropositionTemplate(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val head: Template<List<PropositionComplexVariable>>,
        val body: Template<List<PropositionExpression>>
) : ElementTemplate