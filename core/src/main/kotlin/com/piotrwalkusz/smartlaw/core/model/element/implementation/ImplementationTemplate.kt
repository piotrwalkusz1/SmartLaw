package com.piotrwalkusz.smartlaw.core.model.element.implementation

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.ElementTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRefTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.InterfaceRefTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class ImplementationTemplate(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val interfaceId: Template<InterfaceRefTemplate>,
        val definitionId: Template<DefinitionRefTemplate>,
        val properties: Template<List<PropertyImplementationTemplate>>
) : ElementTemplate