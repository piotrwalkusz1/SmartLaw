package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class PropositionPrimitiveVariable(
        val type: Template<Id>,
        val value: Template<String>
) : PropositionVariable