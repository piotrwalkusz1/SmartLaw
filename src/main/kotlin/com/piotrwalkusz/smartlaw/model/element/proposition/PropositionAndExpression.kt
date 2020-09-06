package com.piotrwalkusz.smartlaw.model.element.proposition

data class PropositionAndExpression(
        val expressions: List<PropositionExpression>
) : PropositionExpression