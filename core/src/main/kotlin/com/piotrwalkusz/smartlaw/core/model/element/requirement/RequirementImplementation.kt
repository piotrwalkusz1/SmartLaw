package com.piotrwalkusz.smartlaw.core.model.element.requirement

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element

data class RequirementImplementation(
        override val id: Id,
        override val annotations: List<Annotation>,
        val requirement: Id,
        val elementsImplementingRequirement: Map<String, Id>
) : Element