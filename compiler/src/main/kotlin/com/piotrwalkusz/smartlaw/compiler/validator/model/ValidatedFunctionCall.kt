package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.element.function.Function
import com.piotrwalkusz.smartlaw.core.model.element.function.FunctionRef

data class ValidatedFunctionCall(
        val function: Function,
        val functionRef: FunctionRef,
        val arguments: List<ValidatedFunctionArgument>
) : ValidatedExpression