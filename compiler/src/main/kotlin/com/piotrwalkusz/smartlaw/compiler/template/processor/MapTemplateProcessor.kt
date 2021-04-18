package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.MapTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template

class MapTemplateProcessor(
        private val templateProcessorService: TemplateProcessorService
) : TemplateProcessor {

    override fun getTemplateType(): Class<*> {
        return MapTemplate::class.java
    }

    override fun <T> processTemplate(template: Template<T>, context: TemplateProcessorContext): T {
        if (template !is MapTemplate<*>) {
            throw IllegalArgumentException("Template must be instance of MapTemplate class")
        }

        @Suppress("UNCHECKED_CAST")
        return processTemplate(template, context) as T
    }

    private fun <T> processTemplate(template: MapTemplate<T>, context: TemplateProcessorContext): Map<String, T> {
        return template.items.mapValues { item -> templateProcessorService.processTemplate(item.value, context) }
    }
}