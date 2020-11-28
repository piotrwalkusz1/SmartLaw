package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class Proposition(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val head: Template<List<PropositionComplexVariable>>,
        val body: Template<List<PropositionExpression>>
) : Element