package com.piotrwalkusz.smartlaw.core.model.document

import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement

interface ConvertibleToNaturalLanguage : Document {

    val name: String
    val presentationElements: List<PresentationElement>
}