package com.piotrwalkusz.smartlaw.core.model.element.function.argument

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class StateVariableRef(
        val state: Id
) : VariableRef

data class StateVariableRefTemplate(
        val state: Template<Id>
) : ComplexTemplate<StateVariableRef> {

    override fun getValue(templateProcessor: ComplexTemplate.TemplateProcessor): StateVariableRef {
        return StateVariableRef(
                state = templateProcessor.processTemplate(state)
        )
    }
}