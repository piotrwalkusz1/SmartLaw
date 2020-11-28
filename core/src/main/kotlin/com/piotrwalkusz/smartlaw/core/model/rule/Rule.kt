package com.piotrwalkusz.smartlaw.core.model.rule

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class Rule(
        val id: Id,
        val description: String? = null,
        val content: Template<String>,
        val arguments: List<MetaArgument> = listOf(),
        val elements: List<Element> = listOf(),
        val ruleInvocations: List<RuleInvocation> = listOf()
)