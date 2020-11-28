package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Statement
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class Function(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val name: Template<String>,
        val description: Template<String?>,
        val parameters: Template<List<GenericParameter>>,
        val arguments: Template<List<FunctionArgumentType>>,
        val body: Template<List<Statement>>,
        val result: Template<FunctionResult?>
) : Element