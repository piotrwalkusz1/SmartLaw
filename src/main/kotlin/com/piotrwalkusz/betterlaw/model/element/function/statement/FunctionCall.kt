package com.piotrwalkusz.betterlaw.model.element.function.statement

import com.piotrwalkusz.betterlaw.model.common.Id
import com.piotrwalkusz.betterlaw.model.element.common.GenericParameter
import com.piotrwalkusz.betterlaw.model.element.function.argument.FunctionArgument

data class FunctionCall(
        val functionId: Id,
        val parameters: List<GenericParameter> = listOf(),
        val arguments: List<FunctionArgument> = listOf()
) : Expression