package com.piotrwalkusz.smartlaw.core.model.rule

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class Rule(
        val id: Id,
        val name: String,
        val description: String? = null,
        val arguments: List<MetaArgument> = listOf(),
        val content: Template<String>,
        val elements: Template<List<Element>> = StaticTemplate(listOf()),
        val outputs: Map<String, Template<*>> = mapOf(),
        val interfaces: List<Id> = listOf()
)