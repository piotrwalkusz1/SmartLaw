package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.model.template.Template

data class PropositionComplexVariable(
        val definition: Template<DefinitionRef>,
        val variables: Template<List<PropositionVariable>>
) : PropositionVariable