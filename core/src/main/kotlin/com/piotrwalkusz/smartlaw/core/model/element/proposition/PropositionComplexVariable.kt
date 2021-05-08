package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef

@GenerateTemplate
data class PropositionComplexVariable(
        val definition: DefinitionRef,
        val variables: List<PropositionVariable>
) : PropositionTerm