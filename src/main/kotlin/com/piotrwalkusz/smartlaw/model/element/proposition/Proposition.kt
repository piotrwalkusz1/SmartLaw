package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.template.Template

data class Proposition(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val head: Template<List<PropositionComplexVariable>>,
        val body: Template<List<PropositionExpression>>
) : Element