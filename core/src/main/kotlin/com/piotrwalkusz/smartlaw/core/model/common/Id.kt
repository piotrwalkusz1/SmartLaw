package com.piotrwalkusz.smartlaw.core.model.common

import com.piotrwalkusz.smartlaw.core.model.template.Template
import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate

data class Id(
        val id: String,
        val namespace: String? = null
)

data class IdTemplate(
        val id: Template<String>,
        val namespace: Template<String?>
) : ComplexTemplate<Id> {

    override fun getValue(templateProcessor: ComplexTemplate.TemplateProcessor): Id {
        return Id(
                id = templateProcessor.processTemplate(id),
                namespace = templateProcessor.processTemplate(namespace)
        )
    }
}