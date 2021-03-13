package com.piotrwalkusz.smartlaw.core.model.element.function.statement

import com.piotrwalkusz.smartlaw.core.model.element.function.argument.VariableRef
import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class Assignment(
        val variable: VariableRef,
        val value: Expression
) : Statement

data class AssignmentTemplate(
        val variable: Template<VariableRef>,
        val value: Template<Expression>
) : ComplexTemplate<Assignment> {

    override fun getValue(templateProcessor: ComplexTemplate.TemplateProcessor): Assignment {
        return Assignment(
                variable = templateProcessor.processTemplate(variable),
                value = templateProcessor.processTemplate(value)
        )
    }
}