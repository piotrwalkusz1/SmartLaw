package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.Template

class TemplateProcessorService {

    private val templateProcessors: List<TemplateProcessor> by lazy {
        listOf(
                StaticTemplateProcessor(),
                TextEngineTemplateProcessor(),
                GroovyTemplateProcessor(),
                ComplexTemplateProcessor(this),
                ListTemplateProcessor(this)
        )
    }

    fun <T> processTemplate(template: Template<T>, templateProcessorContext: TemplateProcessorContext): T {
        val templateProcessor = getTemplateProcessorForTemplate(template)

        return templateProcessor.processTemplate(template, templateProcessorContext)
    }

    private fun <T> getTemplateProcessorForTemplate(template: Template<T>): TemplateProcessor {
        return templateProcessors.find { it.getTemplateType().isAssignableFrom(template.javaClass) }
                ?: throw IllegalArgumentException("Cannot find template processor for class ${template.javaClass}")
    }
}