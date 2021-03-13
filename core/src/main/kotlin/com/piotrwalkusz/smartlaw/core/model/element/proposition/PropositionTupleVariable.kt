package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate

@GenerateTemplate
data class PropositionTupleVariable(
        val variables: List<PropositionVariable>
) : PropositionVariable