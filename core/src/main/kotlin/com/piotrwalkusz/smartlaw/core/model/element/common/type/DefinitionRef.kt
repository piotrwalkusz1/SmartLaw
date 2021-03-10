package com.piotrwalkusz.smartlaw.core.model.element.common.type

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class DefinitionRef(
        val definition: Id,
        val parameters: List<Type> = listOf()
) : Type

data class DefinitionRefTemplate(
        val definition: Template<Id>,
        val parameters: Template<List<Type>>
) : ComplexTemplate<DefinitionRef> {

    override fun getValue(templateProcessor: ComplexTemplate.TemplateProcessor): DefinitionRef {
        return DefinitionRef(
                definition = templateProcessor.processTemplate(definition),
                parameters = templateProcessor.processTemplate(parameters)
        )
    }
}