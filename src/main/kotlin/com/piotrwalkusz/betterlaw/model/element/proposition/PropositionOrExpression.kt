package com.piotrwalkusz.betterlaw.model.element.proposition

data class PropositionOrExpression(
        val expressions: List<PropositionExpression>
) : PropositionExpression