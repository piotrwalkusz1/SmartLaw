package com.piotrwalkusz.smartlaw.core.model.element.function.statement

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.FunctionArgument

data class Operation(
        val operationId: Id,
        val arguments: List<FunctionArgument>
) : Expression