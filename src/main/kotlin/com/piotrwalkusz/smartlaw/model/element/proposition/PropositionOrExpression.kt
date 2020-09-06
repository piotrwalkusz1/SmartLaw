package com.piotrwalkusz.smartlaw.model.element.proposition

data class PropositionOrExpression(
        val expressions: List<PropositionExpression>
) : PropositionExpression