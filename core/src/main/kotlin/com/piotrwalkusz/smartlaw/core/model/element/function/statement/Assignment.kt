package com.piotrwalkusz.smartlaw.core.model.element.function.statement

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.VariableRef

@GenerateTemplate
data class Assignment(
        val variable: VariableRef,
        val value: Expression
) : Statement