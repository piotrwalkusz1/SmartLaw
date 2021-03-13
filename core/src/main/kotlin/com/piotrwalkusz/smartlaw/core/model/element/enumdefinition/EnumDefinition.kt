package com.piotrwalkusz.smartlaw.core.model.element.enumdefinition

import com.piotrwalkusz.smartlaw.core.model.annotation.Annotation
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.ElementTemplate
import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

class EnumDefinition(
        override val id: Id,
        override val annotations: List<Annotation>,
        val name: String,
        val description: String?,
        val variants: List<EnumVariant>
) : Element

data class EnumDefinitionTemplate(
        override val id: Template<Id>,
        override val annotations: Template<List<Annotation>>,
        val name: Template<String>,
        val description: Template<String?>,
        val variants: Template<List<EnumVariant>>
) : ElementTemplate {

    override fun getValue(templateProcessor: ComplexTemplate.TemplateProcessor): Element {
        return EnumDefinition(
                id = templateProcessor.processTemplate(id),
                annotations = templateProcessor.processTemplate(annotations),
                name = templateProcessor.processTemplate(name),
                description = templateProcessor.processTemplate(description),
                variants = templateProcessor.processTemplate(variants)
        )
    }
}