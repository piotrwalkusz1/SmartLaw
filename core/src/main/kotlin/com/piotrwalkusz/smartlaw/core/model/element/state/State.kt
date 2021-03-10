package com.piotrwalkusz.smartlaw.core.model.element.state

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.ElementTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import com.piotrwalkusz.smartlaw.core.model.template.Template
import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate

data class State(
        override val id: Id,
        override val annotations: List<Annotation> = listOf(),
        val name: String,
        val description: String? = null,
        val type: Type,
        val defaultValue: MetaValue? = null
) : Element

data class StateTemplate(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val name: Template<String>,
        val description: Template<String?>,
        val type: Template<Type>,
        val defaultValue: Template<MetaValue?>
) : ElementTemplate {

    override fun getValue(templateProcessor: ComplexTemplate.TemplateProcessor): Element {
        return State(
                id = templateProcessor.processTemplate(id),
                annotations = templateProcessor.processTemplate(annotations),
                name = templateProcessor.processTemplate(name),
                description = templateProcessor.processTemplate(description),
                type = templateProcessor.processTemplate(type),
                defaultValue = templateProcessor.processTemplate(defaultValue),
        )
    }
}