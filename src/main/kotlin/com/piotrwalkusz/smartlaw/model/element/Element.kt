package com.piotrwalkusz.smartlaw.model.element

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.piotrwalkusz.smartlaw.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.`interface`.Interface
import com.piotrwalkusz.smartlaw.model.element.action.ActionDefinition
import com.piotrwalkusz.smartlaw.model.element.actionvalidation.ActionValidation
import com.piotrwalkusz.smartlaw.model.element.definition.Definition
import com.piotrwalkusz.smartlaw.model.element.function.Function
import com.piotrwalkusz.smartlaw.model.element.implementation.Implementation
import com.piotrwalkusz.smartlaw.model.element.proposition.Proposition
import com.piotrwalkusz.smartlaw.model.element.reference.Reference
import com.piotrwalkusz.smartlaw.model.element.state.State
import com.piotrwalkusz.smartlaw.model.template.Template

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
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
    val id: Template<Id>
    val annotations: Template<List<Annotation>>
}