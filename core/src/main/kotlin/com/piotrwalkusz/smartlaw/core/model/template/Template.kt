package com.piotrwalkusz.smartlaw.core.model.template

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.piotrwalkusz.smartlaw.core.model.common.IdTemplate

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "templateType")
@JsonSubTypes(
        JsonSubTypes.Type(name = "Static", value = StaticTemplate::class),
        JsonSubTypes.Type(name = "TextEngine", value = TextEngineTemplate::class),
        JsonSubTypes.Type(name = "Groovy", value = GroovyTemplate::class),
        JsonSubTypes.Type(name = "IdTemplate", value = IdTemplate::class),
)
interface Template<T>