package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.element.definition.Definition

data class ValidatedDefinition(
        val name: String,
        val definition: Definition,
        val properties: List<ValidatedDefinitionProperty>
) : ValidatedElement