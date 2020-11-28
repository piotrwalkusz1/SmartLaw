package com.piotrwalkusz.smartlaw.core.model.document

import com.piotrwalkusz.smartlaw.core.model.rule.textformatter.RuleInvocationTextFormatter

interface ConvertibleToNaturalLanguage : Document {

    val name: String
    val ruleInvocationTextFormatters: List<RuleInvocationTextFormatter>
}