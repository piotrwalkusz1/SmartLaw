package com.piotrwalkusz.smartlaw.service.controller.dto

import com.piotrwalkusz.smartlaw.compiler.common.output.OutputMessage
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.ExtendedPresentationElement

data class ExtendPresentationElementsResultDto(
        val extendedPresentationElements: List<ExtendedPresentationElement<*, *>>,
        val outputMessages: List<OutputMessage>
)