package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.element.annotation.Annotation

data class Proposition(
        override val id: Id,
        override val annotations: List<Annotation>,
        val head: List<PropositionComplexVariable>,
        val body: List<PropositionExpression>
) : Element