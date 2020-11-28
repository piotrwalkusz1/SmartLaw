package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.Template

class TemplateProcessorService(
        private val templateProcessors: List<TemplateProcessor<*, *>> = TemplateProcessor.DEFAULT_TEMPLATE_PROCESSORS
) {

    fun <T> processTemplate(template: Template<T>, templateProcessorContext: TemplateProcessorContext): T {
        val templateProcessor = getTemplateProcessorForTemplate(template)

        return templateProcessor.processTemplate(template, templateProcessorContext)
    }

    private fun <T> getTemplateProcessorForTemplate(template: Template<T>): TemplateProcessor<Template<T>, T> {
        @Suppress("UNCHECKED_CAST")
        return templateProcessors.find { it.templateType.isAssignableFrom(template.javaClass) } as TemplateProcessor<Template<T>, T>?
                ?: throw IllegalArgumentException("Cannot find template processor for class ${template.javaClass}")
    }
}