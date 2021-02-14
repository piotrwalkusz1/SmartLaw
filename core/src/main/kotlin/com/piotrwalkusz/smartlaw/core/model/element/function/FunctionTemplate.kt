package com.piotrwalkusz.smartlaw.core.model.element.function

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.ElementTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.element.common.GenericParameterTemplate
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Statement
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class FunctionTemplate(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val name: Template<String>,
        val description: Template<String?>,
        val parameters: Template<List<GenericParameterTemplate>>,
        val arguments: Template<List<FunctionArgumentType>>,
        val body: Template<List<Statement>>,
        val result: Template<FunctionResult?>
) : ElementTemplate