package com.piotrwalkusz.betterlaw.model.element.proposition

import com.piotrwalkusz.betterlaw.model.common.Id
import com.piotrwalkusz.betterlaw.model.element.Element
import com.piotrwalkusz.betterlaw.model.element.annotation.Annotation

data class Proposition(
        override val id: Id,
        override val annotations: List<Annotation>,
        val head: List<PropositionComplexVariable>,
        val body: List<PropositionExpression>
) : Element