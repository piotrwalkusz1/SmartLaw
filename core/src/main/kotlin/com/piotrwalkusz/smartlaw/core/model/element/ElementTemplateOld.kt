package com.piotrwalkusz.smartlaw.core.model.element

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.`interface`.InterfaceTemplate
import com.piotrwalkusz.smartlaw.core.model.element.action.ActionDefinitionTemplate
import com.piotrwalkusz.smartlaw.core.model.element.actionvalidation.ActionValidationTemplate
import com.piotrwalkusz.smartlaw.core.model.element.attachment.AttachmentTemplate
import com.piotrwalkusz.smartlaw.core.model.element.definition.DefinitionTemplate
import com.piotrwalkusz.smartlaw.core.model.element.function.FunctionTemplate
import com.piotrwalkusz.smartlaw.core.model.element.implementation.ImplementationTemplate
import com.piotrwalkusz.smartlaw.core.model.element.proposition.PropositionTemplate
import com.piotrwalkusz.smartlaw.core.model.element.reference.ReferenceTemplate
import com.piotrwalkusz.smartlaw.core.model.element.state.StateTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "elementType")
@JsonSubTypes(
        JsonSubTypes.Type(name = "ActionDefinition", value = ActionDefinitionTemplate::class),
        JsonSubTypes.Type(name = "ActionValidation", value = ActionValidationTemplate::class),
        JsonSubTypes.Type(name = "Function", value = FunctionTemplate::class),
        JsonSubTypes.Type(name = "Interface", value = InterfaceTemplate::class),
        JsonSubTypes.Type(name = "Implementation", value = ImplementationTemplate::class),
        JsonSubTypes.Type(name = "Proposition", value = PropositionTemplate::class),
        JsonSubTypes.Type(name = "Definition", value = DefinitionTemplate::class),
        JsonSubTypes.Type(name = "Reference", value = ReferenceTemplate::class)
)
interface ElementTemplateOld {
    val id: Template<Id>
    val annotations: Template<List<Annotation>>
}