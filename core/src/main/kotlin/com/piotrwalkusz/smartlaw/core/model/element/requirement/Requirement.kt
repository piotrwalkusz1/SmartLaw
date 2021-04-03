package com.piotrwalkusz.smartlaw.core.model.element.requirement

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element

@GenerateTemplate
data class Requirement(
        override val id: Id,
        override val annotations: List<Annotation>,
        val parentRequirement: Id?,
        val requiredElementsCodes: List<String>,
        val validators: List<RequirementImplementationValidator>
) : Element