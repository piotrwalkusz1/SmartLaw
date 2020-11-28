package com.piotrwalkusz.smartlaw.core.model.rule.textformatter

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "_type")
@JsonSubTypes(
        JsonSubTypes.Type(name = "IndentationRuleInvocationTextFormatter", value = IndentationRuleInvocationTextFormatter::class),
        JsonSubTypes.Type(name = "SimpleRuleInvocationTextFormatter", value = SimpleRuleInvocationTextFormatter::class)
)
interface RuleInvocationTextFormatter