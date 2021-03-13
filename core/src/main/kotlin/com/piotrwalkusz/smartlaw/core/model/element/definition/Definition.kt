package com.piotrwalkusz.smartlaw.core.model.element.definition

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef

@GenerateTemplate
data class Definition(
        override val id: Id,
        override val annotations: List<Annotation>,
        val name: String,
        val description: String?,
        val parameters: List<GenericParameter>,
        val properties: List<DefinitionProperty>,
        val baseDefinitions: List<DefinitionRef>
) : Element