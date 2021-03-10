package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

class StaticTemplateProcessor : TemplateProcessor {

    override fun getTemplateType(): Class<*> {
        return StaticTemplate::class.java
    }

    override fun <T> processTemplate(template: Template<T>, context: TemplateProcessorContext): T {
        if (template !is StaticTemplate) {
            throw IllegalArgumentException("Template must be instance of StaticTemplate class")
        }

        return template.value
    }
}