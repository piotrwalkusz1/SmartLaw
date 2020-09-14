package com.piotrwalkusz.smartlaw.model.meta

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes(
        JsonSubTypes.Type(name = "Primitive", value = MetaPrimitiveValue::class),
        JsonSubTypes.Type(name = "Complex", value = MetaComplexValue::class)
)
interface MetaValue