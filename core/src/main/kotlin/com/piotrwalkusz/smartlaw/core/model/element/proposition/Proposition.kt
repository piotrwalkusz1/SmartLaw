package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element

data class Proposition(
        override val id: Id,
        override val annotations: List<Annotation>,
        val head: List<PropositionComplexVariable>,
        val body: List<PropositionExpression>
) : Element