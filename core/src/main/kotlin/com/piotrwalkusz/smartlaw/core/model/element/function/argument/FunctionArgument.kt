package com.piotrwalkusz.smartlaw.core.model.element.function.argument

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(name = "StateVariableRef", value = StateVariableRef::class),
)
interface FunctionArgument