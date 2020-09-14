package com.piotrwalkusz.smartlaw.model.template

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes(
        JsonSubTypes.Type(name = "StaticTemplate", value = StaticTemplate::class),
        JsonSubTypes.Type(name = "TextEngineTemplate", value = TextEngineTemplate::class)
)
interface Template<T>