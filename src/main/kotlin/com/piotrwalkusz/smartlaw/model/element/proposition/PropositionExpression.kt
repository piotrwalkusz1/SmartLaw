package com.piotrwalkusz.smartlaw.model.element.proposition

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes(
        JsonSubTypes.Type(name = "Complex", value = PropositionComplexVariable::class),
        JsonSubTypes.Type(name = "Function", value = PropositionFunctionVariable::class),
        JsonSubTypes.Type(name = "Primitive", value = PropositionPrimitiveVariable::class),
        JsonSubTypes.Type(name = "Tuple", value = PropositionTupleVariable::class)
)
interface PropositionExpression