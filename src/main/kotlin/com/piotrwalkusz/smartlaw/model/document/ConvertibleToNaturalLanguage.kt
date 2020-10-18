package com.piotrwalkusz.smartlaw.model.document

import com.piotrwalkusz.smartlaw.model.rule.textformatter.RuleInvocationTextFormatter

interface ConvertibleToNaturalLanguage : Document {

    val ruleInvocationTextFormatters: List<RuleInvocationTextFormatter>
}