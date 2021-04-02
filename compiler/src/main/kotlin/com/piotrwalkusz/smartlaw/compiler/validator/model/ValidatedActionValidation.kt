package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.element.action.ActionDefinition

data class ValidatedActionValidation(
        val name: String,
        val action: ActionDefinition,
        val condition: ValidatedExpression
) : ValidatedElement