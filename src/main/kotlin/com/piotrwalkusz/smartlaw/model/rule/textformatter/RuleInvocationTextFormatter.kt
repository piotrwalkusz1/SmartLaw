package com.piotrwalkusz.smartlaw.model.rule.textformatter

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes(
        JsonSubTypes.Type(name = "IndentationRuleInvocationTextFormatter", value = IndentationRuleInvocationTextFormatter::class),
        JsonSubTypes.Type(name = "SimpleRuleInvocationTextFormatter", value = SimpleRuleInvocationTextFormatter::class)
)
interface RuleInvocationTextFormatter