package com.piotrwalkusz.smartlaw.core.model.template

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "_type")
@JsonSubTypes(
        JsonSubTypes.Type(name = "StaticTemplate", value = StaticTemplate::class),
        JsonSubTypes.Type(name = "TextEngineTemplate", value = TextEngineTemplate::class)
)
interface Template<T>