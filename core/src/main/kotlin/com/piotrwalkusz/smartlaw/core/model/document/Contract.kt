package com.piotrwalkusz.smartlaw.core.model.document

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement

data class Contract(
        override val id: Id,
        override val name: String,
        val description: String? = null,
        override val presentationElements: List<PresentationElement> = listOf()
) : ConvertibleToNaturalLanguage