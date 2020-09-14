package com.piotrwalkusz.smartlaw.model.document

import com.piotrwalkusz.smartlaw.model.annotation.AnnotationType
import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.rule.Rule

data class Library(
        val id: Id,
        val name: String,
        val description: String? = null,
        var rules: List<Rule> = listOf(),
        var elements: List<Element> = listOf(),
        var annotations: List<AnnotationType> = listOf()
) : Document