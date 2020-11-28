package com.piotrwalkusz.smartlaw.core.model.document

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.rule.textformatter.RuleInvocationTextFormatter

data class Contract(
        override val id: Id,
        override val name: String,
        val description: String? = null,
        override val ruleInvocationTextFormatters: List<RuleInvocationTextFormatter> = listOf()
) : ConvertibleToNaturalLanguage