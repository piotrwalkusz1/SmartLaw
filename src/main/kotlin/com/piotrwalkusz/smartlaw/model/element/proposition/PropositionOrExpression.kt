package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.template.Template

data class PropositionOrExpression(
        val expressions: Template<List<PropositionExpression>>
) : PropositionExpression