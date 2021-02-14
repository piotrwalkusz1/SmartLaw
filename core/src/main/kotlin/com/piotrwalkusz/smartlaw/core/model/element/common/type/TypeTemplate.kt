package com.piotrwalkusz.smartlaw.core.model.element.common.type

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(name = "DefinitionRef", value = DefinitionRefTemplate::class),
        JsonSubTypes.Type(name = "GenericType", value = GenericTypeTemplate::class),
        JsonSubTypes.Type(name = "InterfaceRef", value = InterfaceRefTemplate::class)
)
interface TypeTemplate