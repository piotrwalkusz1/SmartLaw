package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.element.function.FunctionRef
import com.piotrwalkusz.smartlaw.model.template.Template

data class PropositionFunctionVariable(
        val function: Template<FunctionRef>,
        val variables: Template<List<PropositionVariable>>
) : PropositionVariable