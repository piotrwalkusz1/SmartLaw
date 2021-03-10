package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.Template
import com.piotrwalkusz.smartlaw.core.model.template.ComplexTemplate

class ComplexTemplateProcessor(
        private val templateProcessorService: TemplateProcessorService
) : TemplateProcessor {

    override fun getTemplateType(): Class<*> {
        return ComplexTemplate::class.java
    }

    override fun <T> processTemplate(template: Template<T>, context: TemplateProcessorContext): T {
        if (template !is ComplexTemplate) {
            throw IllegalArgumentException("Template must be instance of ComplexTemplate class")
        }

        return template.getValue(object : ComplexTemplate.TemplateProcessor {
            override fun <Q> processTemplate(template: Template<Q>): Q {
                return templateProcessorService.processTemplate(template, context)
            }
        })
    }
}