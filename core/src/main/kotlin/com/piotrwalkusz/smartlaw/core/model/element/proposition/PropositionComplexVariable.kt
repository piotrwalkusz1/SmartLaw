package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class PropositionComplexVariable(
        val definition: Template<DefinitionRef>,
        val variables: Template<List<PropositionVariable>>
) : PropositionVariable