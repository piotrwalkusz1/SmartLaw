package com.piotrwalkusz.smartlaw.model.document

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.model.rule.textformatter.RuleInvocationTextFormatter

data class Contract(
        override val id: Id,
        override val name: String,
        val description: String? = null,
        override val ruleInvocationTextFormatters: List<RuleInvocationTextFormatter> = listOf()
) : ConvertibleToNaturalLanguage