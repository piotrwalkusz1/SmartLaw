package com.piotrwalkusz.betterlaw.model.element.proposition

data class PropositionAndExpression(
        val expressions: List<PropositionExpression>
) : PropositionExpression