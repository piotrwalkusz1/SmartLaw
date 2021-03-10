package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRefTemplateOld
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class PropositionComplexVariable(
        val definition: Template<DefinitionRefTemplateOld>,
        val variables: Template<List<PropositionVariable>>
) : PropositionVariable