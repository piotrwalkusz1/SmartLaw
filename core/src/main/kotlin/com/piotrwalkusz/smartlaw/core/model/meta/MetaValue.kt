package com.piotrwalkusz.smartlaw.core.model.meta

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(name = "Primitive", value = MetaPrimitiveValue::class),
        JsonSubTypes.Type(name = "List", value = MetaListValue::class),
        JsonSubTypes.Type(name = "Map", value = MetaMapValue::class)
)
interface MetaValue