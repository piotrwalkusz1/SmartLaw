package com.piotrwalkusz.smartlaw.core.model.meta

import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

data class MetaPrimitiveValue(
        val value: String
) : MetaValue

data class MetaPrimitiveValueTemplate(
        val value: Template<String>
) : ComplexTemplate<MetaPrimitiveValue> {

    override fun getValue(templateProcessor: ComplexTemplate.TemplateProcessor): MetaPrimitiveValue {
        return MetaPrimitiveValue(
                value = templateProcessor.processTemplate(value)
        )
    }
}