package com.piotrwalkusz.smartlaw.model.element.proposition

import com.piotrwalkusz.smartlaw.model.template.Template

data class PropositionAndExpression(
        val expressions: Template<List<PropositionExpression>>
) : PropositionExpression