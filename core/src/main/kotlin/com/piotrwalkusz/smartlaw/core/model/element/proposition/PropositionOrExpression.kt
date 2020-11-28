package com.piotrwalkusz.smartlaw.core.model.element.proposition

import com.piotrwalkusz.smartlaw.core.model.template.Template

data class PropositionOrExpression(
        val expressions: Template<List<PropositionExpression>>
) : PropositionExpression