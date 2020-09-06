package com.piotrwalkusz.betterlaw.model.element.annotation

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes(
        JsonSubTypes.Type(name = "Primitive", value = AnnotationPrimitiveValue::class),
        JsonSubTypes.Type(name = "Complex", value = AnnotationComplexValue::class)
)
interface AnnotationValue