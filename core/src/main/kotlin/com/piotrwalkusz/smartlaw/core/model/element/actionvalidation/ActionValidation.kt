package com.piotrwalkusz.smartlaw.core.model.element.actionvalidation

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.action.ActionRef
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Expression

@GenerateTemplate
data class ActionValidation(
        override val id: Id,
        override val annotations: List<Annotation>,
        val name: String,
        val description: String? = null,
        val action: ActionRef,
        val condition: Expression
) : Element