package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.common.Id

@GenerateTemplate
data class PropositionPrimitiveVariable(
        val type: Id,
        val value: String
) : PropositionVariable