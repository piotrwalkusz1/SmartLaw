package com.piotrwalkusz.smartlaw.core.model.document

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.piotrwalkusz.smartlaw.core.model.common.Id

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "documentType")
@JsonSubTypes(
        JsonSubTypes.Type(name = "Contract", value = Contract::class),
        JsonSubTypes.Type(name = "LegalAct", value = LegalAct::class),
        JsonSubTypes.Type(name = "Library", value = Library::class)
)
interface Document {

    val id: Id
}