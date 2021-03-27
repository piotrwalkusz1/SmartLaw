package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.element.enumdefinition.EnumDefinition

data class ValidatedEnumDefinitionRef(
        val enumDefinition: EnumDefinition
) : ValidatedType