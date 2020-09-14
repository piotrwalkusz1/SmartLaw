package com.piotrwalkusz.smartlaw.model.element.actionvalidation

import com.piotrwalkusz.smartlaw.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.Element
import com.piotrwalkusz.smartlaw.model.element.action.ActionRef
import com.piotrwalkusz.smartlaw.model.element.function.statement.Expression
import com.piotrwalkusz.smartlaw.model.template.Template

data class ActionValidation(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val name: Template<String>,
        val description: Template<String>? = null,
        val action: Template<ActionRef>,
        val condition: Template<Expression>
) : Element