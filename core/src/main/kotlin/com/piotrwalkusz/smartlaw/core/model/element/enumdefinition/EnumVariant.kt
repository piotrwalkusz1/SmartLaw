package com.piotrwalkusz.smartlaw.core.model.element.enumdefinition

import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class EnumVariant(
        val name: String,
        val description: String?
)

data class EnumVariantTemplate(
        val name: Template<String>,
        val description: Template<String?>
) : ComplexTemplate<EnumVariant> {

    override fun getValue(templateProcessor: ComplexTemplate.TemplateProcessor): EnumVariant {
        return EnumVariant(
                name = templateProcessor.processTemplate(name),
                description = templateProcessor.processTemplate(description)
        )
    }
}