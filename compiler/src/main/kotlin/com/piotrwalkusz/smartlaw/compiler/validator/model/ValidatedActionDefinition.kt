package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.element.action.ActionDefinition
import com.piotrwalkusz.smartlaw.core.model.element.function.Function
import com.piotrwalkusz.smartlaw.core.model.element.function.FunctionRef

data class ValidatedActionDefinition(
        val action: ActionDefinition,
        val name: String,
        val arguments: List<ValidatedActionArgument>,
        val function: Function,
        val functionRef: FunctionRef
) : ValidatedElement