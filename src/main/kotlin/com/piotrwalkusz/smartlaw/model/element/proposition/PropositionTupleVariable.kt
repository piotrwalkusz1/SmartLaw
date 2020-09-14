package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.template.Template

data class PropositionTupleVariable(
        val variables: Template<List<PropositionVariable>>
) : PropositionVariable