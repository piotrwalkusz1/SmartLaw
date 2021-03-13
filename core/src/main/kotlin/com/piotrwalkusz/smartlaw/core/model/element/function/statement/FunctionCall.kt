package com.piotrwalkusz.smartlaw.core.model.element.function.statement

import com.piotrwalkusz.smartlaw.core.model.element.function.FunctionRef
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.FunctionArgument

data class FunctionCall(
        val function: FunctionRef,
        val arguments: List<FunctionArgument>
) : Expression