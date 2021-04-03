package com.piotrwalkusz.smartlaw.core.model.document

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element

data class LegalAct(
        override val id: Id,
        val name: String,
        val description: String? = null,
        val elements: List<Element>
) : Document