package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Statement

@GenerateTemplate
data class Function(
        override val id: Id,
        override val annotations: List<Annotation>,
        val name: String,
        val description: String?,
        val parameters: List<GenericParameter>,
        val arguments: List<FunctionArgumentDefinition>,
        val body: List<Statement>,
        val result: FunctionResult?
) : Element