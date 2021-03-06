package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.Template
import com.piotrwalkusz.smartlaw.core.model.validator.ComplexTemplate

class ComplexTemplateProcessor(
        private val templateProcessorService: TemplateProcessorService
) : TemplateProcessor<ComplexTemplate<Any>, Any> {

    override val templateType: Class<ComplexTemplate<Any>>
        @Suppress("UNCHECKED_CAST")
        get() = ComplexTemplate::class.java as Class<ComplexTemplate<Any>>

    override val resultType: Class<Any>
        get() = Any::class.java

    override fun processTemplate(template: ComplexTemplate<Any>, context: TemplateProcessorContext): Any {
        return template.getValue(object : ComplexTemplate.TemplateProcessor {
            override fun <Q> processTemplate(template: Template<Q>): Q {
                return templateProcessorService.processTemplate(template, context)
            }
        })
    }
}