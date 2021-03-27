package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.element.definition.Definition

data class ValidatedDefinitionRef(
        val definition: Definition
) : ValidatedType