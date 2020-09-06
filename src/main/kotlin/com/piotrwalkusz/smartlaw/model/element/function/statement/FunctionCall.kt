package com.piotrwalkusz.smartlaw.model.element.function.statement

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.model.element.function.argument.FunctionArgument

data class FunctionCall(
        val functionId: Id,
        val parameters: List<GenericParameter> = listOf(),
        val arguments: List<FunctionArgument> = listOf()
) : Expression