package com.piotrwalkusz.smartlaw.core.model.validator

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(name = "Regex", value = RegexValidator::class),
        JsonSubTypes.Type(name = "Generic", value = GenericValidator::class),
)
interface Validator