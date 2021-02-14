package com.piotrwalkusz.smartlaw.core.model.template

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "templateType")
@JsonSubTypes(
        JsonSubTypes.Type(name = "Static", value = StaticTemplate::class),
        JsonSubTypes.Type(name = "TextEngine", value = TextEngineTemplate::class),
        JsonSubTypes.Type(name = "Groovy", value = GroovyTemplate::class)
)
interface Template<T>