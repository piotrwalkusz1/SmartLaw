package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.GenericParameterTemplate
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Statement

data class Function(
        override val id: Id,
        override val annotations: List<Annotation>,
        val name: String,
        val description: String?,
        val parameters: List<GenericParameterTemplate>,
        val arguments: List<FunctionArgumentType>,
        val body: List<Statement>,
        val result: FunctionResult?
) : Element