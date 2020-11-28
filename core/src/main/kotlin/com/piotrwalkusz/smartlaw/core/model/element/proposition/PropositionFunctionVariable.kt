package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.core.model.element.function.FunctionRef
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class PropositionFunctionVariable(
        val function: Template<FunctionRef>,
        val variables: Template<List<PropositionVariable>>
) : PropositionVariable