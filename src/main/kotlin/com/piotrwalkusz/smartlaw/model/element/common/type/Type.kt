package com.piotrwalkusz.smartlaw.model.element.common.type

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes(
        JsonSubTypes.Type(name = "DefinitionRef", value = DefinitionRef::class),
        JsonSubTypes.Type(name = "GenericType", value = GenericType::class),
        JsonSubTypes.Type(name = "InterfaceRef", value = InterfaceRef::class)
)
interface Type