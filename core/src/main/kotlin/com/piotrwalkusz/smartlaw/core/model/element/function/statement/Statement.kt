package com.piotrwalkusz.smartlaw.core.model.element.function.statement

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.StateVariableRef

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "statementType")
@JsonSubTypes(
        JsonSubTypes.Type(name = "Assignment", value = Assignment::class),
        JsonSubTypes.Type(name = "EnumValue", value = EnumValue::class),
        JsonSubTypes.Type(name = "FunctionCall", value = FunctionCall::class),
        JsonSubTypes.Type(name = "Operation", value = Operation::class),
        JsonSubTypes.Type(name = "ConstantValue", value = ConstantValue::class),
        JsonSubTypes.Type(name = "StateVariableRef", value = StateVariableRef::class),
)
interface Statement