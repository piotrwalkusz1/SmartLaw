package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element

@GenerateTemplate
data class Proposition(
        override val id: Id,
        override val annotations: List<Annotation>,
        val head: List<PropositionTerm>,
        val body: List<PropositionExpression>
) : Element