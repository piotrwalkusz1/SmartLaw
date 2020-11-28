package com.piotrwalkusz.smartlaw.core.model.element.function.statement

import com.piotrwalkusz.smartlaw.core.model.element.function.FunctionRef
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.FunctionArgument
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class FunctionCall(
        val function: Template<FunctionRef>,
        val arguments: Template<List<FunctionArgument>>
) : Expression