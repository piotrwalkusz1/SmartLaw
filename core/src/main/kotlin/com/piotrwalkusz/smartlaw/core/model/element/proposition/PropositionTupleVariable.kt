package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.core.model.template.Template

data class PropositionTupleVariable(
        val variables: Template<List<PropositionVariable>>
) : PropositionVariable