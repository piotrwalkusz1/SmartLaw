package com.piotrwalkusz.smartlaw.core.model.template

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.piotrwalkusz.smartlaw.core.model.common.IdTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRefTemplate
import com.piotrwalkusz.smartlaw.core.model.element.enumdefinition.EnumDefinitionTemplate
import com.piotrwalkusz.smartlaw.core.model.element.enumdefinition.EnumVariantTemplate
import com.piotrwalkusz.smartlaw.core.model.element.state.StateTemplate
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValueTemplate

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "templateType")
@JsonSubTypes(
        JsonSubTypes.Type(name = "Static", value = StaticTemplate::class),
        JsonSubTypes.Type(name = "TextEngine", value = TextEngineTemplate::class),
        JsonSubTypes.Type(name = "Groovy", value = GroovyTemplate::class),
        JsonSubTypes.Type(name = "ListTemplate", value = ListTemplate::class),
        JsonSubTypes.Type(name = "StateTemplate", value = StateTemplate::class),
        JsonSubTypes.Type(name = "EnumDefinitionTemplate", value = EnumDefinitionTemplate::class),
        JsonSubTypes.Type(name = "EnumVariantTemplate", value = EnumVariantTemplate::class),
        JsonSubTypes.Type(name = "IdTemplate", value = IdTemplate::class),
        JsonSubTypes.Type(name = "DefinitionRefTemplate", value = DefinitionRefTemplate::class),
        JsonSubTypes.Type(name = "MetaPrimitiveValueTemplate", value = MetaPrimitiveValueTemplate::class),
)
interface Template<T>