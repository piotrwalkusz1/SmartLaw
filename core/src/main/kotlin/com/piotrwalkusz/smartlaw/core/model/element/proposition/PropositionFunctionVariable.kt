package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.function.FunctionRef

@GenerateTemplate
data class PropositionFunctionVariable(
        val function: FunctionRef,
        val variables: List<PropositionVariable>
) : PropositionVariable