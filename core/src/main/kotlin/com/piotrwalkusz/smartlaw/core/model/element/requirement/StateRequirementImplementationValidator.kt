package com.piotrwalkusz.smartlaw.core.model.element.requirement

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate

@GenerateTemplate
data class StateRequirementImplementationValidator(
        val elementCode: String
) : RequirementImplementationValidator