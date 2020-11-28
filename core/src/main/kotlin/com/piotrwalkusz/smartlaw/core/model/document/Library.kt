package com.piotrwalkusz.smartlaw.core.model.document

import com.piotrwalkusz.smartlaw.core.model.annotation.AnnotationType
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.rule.Rule

data class Library(
        override val id: Id,
        val name: String,
        val description: String? = null,
        override var rules: List<Rule> = listOf(),
        var elements: List<Element> = listOf(),
        var annotations: List<AnnotationType> = listOf()
) : RulesContainer