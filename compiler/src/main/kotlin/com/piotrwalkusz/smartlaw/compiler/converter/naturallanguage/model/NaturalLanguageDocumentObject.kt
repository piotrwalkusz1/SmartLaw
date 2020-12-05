package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(name = "Provision", value = NaturalLanguageProvision::class),
        JsonSubTypes.Type(name = "Section", value = NaturalLanguageSection::class)
)
interface NaturalLanguageDocumentObject