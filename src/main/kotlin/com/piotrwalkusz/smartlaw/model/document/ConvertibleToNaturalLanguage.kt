package com.piotrwalkusz.smartlaw.model.document

import com.piotrwalkusz.smartlaw.model.rule.textformatter.RuleInvocationTextFormatter

interface ConvertibleToNaturalLanguage : Document {

    val name: String
    val ruleInvocationTextFormatters: List<RuleInvocationTextFormatter>
}