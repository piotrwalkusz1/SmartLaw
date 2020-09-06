package com.piotrwalkusz.smartlaw.model.element.definition

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.element.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.model.element.common.type.DefinitionRef

data class Definition(
        override val id: Id,
        override val annotations: List<Annotation> = listOf(),
        val name: String,
        val description: String? = null,
        val parameters: List<GenericParameter> = listOf(),
        val properties: List<DefinitionProperty> = listOf(),
        val baseDefinitions: List<DefinitionRef> = listOf()
) : Element