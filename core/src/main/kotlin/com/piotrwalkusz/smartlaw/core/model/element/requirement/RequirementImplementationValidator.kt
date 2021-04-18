package com.piotrwalkusz.smartlaw.core.model.element.requirement

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(name = "StateRequirementImplementationValidator", value = StateRequirementImplementationValidator::class)
)
interface RequirementImplementationValidator