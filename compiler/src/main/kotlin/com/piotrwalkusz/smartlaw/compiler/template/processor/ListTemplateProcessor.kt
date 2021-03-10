package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.ListTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

class ListTemplateProcessor(
        private val templateProcessorService: TemplateProcessorService
) : TemplateProcessor {

    override fun getTemplateType(): Class<*> {
        return ListTemplate::class.java
    }

    override fun <T> processTemplate(template: Template<T>, context: TemplateProcessorContext): T {
        if (template !is ListTemplate<*, *>) {
            throw IllegalArgumentException("Template must be instance of ListTemplate class")
        }

        @Suppress("UNCHECKED_CAST")
        return processTemplate(template, context) as T
    }

    private fun <T : Template<R>, R> processTemplate(template: ListTemplate<T, R>, context: TemplateProcessorContext): List<R> {
        return template.items.map { item -> templateProcessorService.processTemplate(item, context) }
    }
}