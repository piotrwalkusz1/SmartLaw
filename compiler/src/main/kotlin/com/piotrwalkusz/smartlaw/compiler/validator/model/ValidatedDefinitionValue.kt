package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.element.definition.Definition

data class ValidatedDefinitionValue(
        val values: Map<String, ValidatedValue>,
        val definition: Definition
) : ValidatedValue