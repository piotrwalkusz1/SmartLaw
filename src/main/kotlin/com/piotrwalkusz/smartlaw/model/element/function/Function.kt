package com.piotrwalkusz.smartlaw.model.element.function

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.element.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.model.element.function.statement.Statement

data class Function(
        override val id: Id,
        override val annotations: List<Annotation> = listOf(),
        val name: String,
        val description: String? = null,
        val parameters: List<GenericParameter> = listOf(),
        val arguments: List<FunctionArgumentType> = listOf(),
        val body: List<Statement> = listOf(),
        val result: FunctionResult? = null
) : Element