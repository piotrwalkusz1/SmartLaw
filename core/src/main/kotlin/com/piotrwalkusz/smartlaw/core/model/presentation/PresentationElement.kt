package com.piotrwalkusz.smartlaw.core.model.presentation

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(name = "Section", value = SectionPresentationElement::class),
        JsonSubTypes.Type(name = "RuleInvocation", value = RuleInvocationPresentationElement::class)
)
interface PresentationElement