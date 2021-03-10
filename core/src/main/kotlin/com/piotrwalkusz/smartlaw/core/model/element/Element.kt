package com.piotrwalkusz.smartlaw.core.model.element

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.`interface`.Interface
import com.piotrwalkusz.smartlaw.core.model.element.action.ActionDefinition
import com.piotrwalkusz.smartlaw.core.model.element.actionvalidation.ActionValidation
import com.piotrwalkusz.smartlaw.core.model.element.definition.Definition
import com.piotrwalkusz.smartlaw.core.model.element.function.Function
import com.piotrwalkusz.smartlaw.core.model.element.implementation.Implementation
import com.piotrwalkusz.smartlaw.core.model.element.proposition.Proposition
import com.piotrwalkusz.smartlaw.core.model.element.reference.Reference
import com.piotrwalkusz.smartlaw.core.model.element.state.State
import com.piotrwalkusz.smartlaw.core.model.template.Template
import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "elementType")
@JsonSubTypes(
        JsonSubTypes.Type(name = "ActionDefinition", value = ActionDefinition::class),
        JsonSubTypes.Type(name = "ActionValidation", value = ActionValidation::class),
        JsonSubTypes.Type(name = "Function", value = Function::class),
        JsonSubTypes.Type(name = "Interface", value = Interface::class),
        JsonSubTypes.Type(name = "Implementation", value = Implementation::class),
        JsonSubTypes.Type(name = "Proposition", value = Proposition::class),
        JsonSubTypes.Type(name = "State", value = State::class),
        JsonSubTypes.Type(name = "Definition", value = Definition::class),
        JsonSubTypes.Type(name = "Reference", value = Reference::class)
)
interface Element {
    val id: Id
    val annotations: List<Annotation>
}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "elementType")
@JsonSubTypes(
        JsonSubTypes.Type(name = "State", value = State::class),
)
interface ElementTemplate : ComplexTemplate<Element> {
    val id: Template<Id>
    val annotations: Template<List<Annotation>>
}