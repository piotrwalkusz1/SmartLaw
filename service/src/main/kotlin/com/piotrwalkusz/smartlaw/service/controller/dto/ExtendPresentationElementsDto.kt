package com.piotrwalkusz.smartlaw.service.controller.dto

import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement

data class ExtendPresentationElementsDto(
        val allPresentationElements: List<PresentationElement>,
        val presentationElementsToExtend: List<PresentationElement>
)