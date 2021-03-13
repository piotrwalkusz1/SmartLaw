package com.piotrwalkusz.smartlaw.core.model.element.enumdefinition

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element

@GenerateTemplate
class EnumDefinition(
        override val id: Id,
        override val annotations: List<Annotation>,
        val name: String,
        val description: String?,
        val variants: List<EnumVariant>
) : Element