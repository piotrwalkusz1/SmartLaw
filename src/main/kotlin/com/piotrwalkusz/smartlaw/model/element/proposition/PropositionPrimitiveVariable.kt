package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.template.Template

data class PropositionPrimitiveVariable(
        val type: Template<Id>,
        val value: Template<String>
) : PropositionVariable