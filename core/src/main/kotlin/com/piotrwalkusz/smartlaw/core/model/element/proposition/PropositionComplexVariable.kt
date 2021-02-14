package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRefTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class PropositionComplexVariable(
        val definition: Template<DefinitionRefTemplate>,
        val variables: Template<List<PropositionVariable>>
) : PropositionVariable