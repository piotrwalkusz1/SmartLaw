package com.piotrwalkusz.smartlaw.core.model.element.actionvalidation

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.action.ActionRef
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Expression
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class ActionValidation(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val name: Template<String>,
        val description: Template<String>? = null,
        val action: Template<ActionRef>,
        val condition: Template<Expression>
) : Element