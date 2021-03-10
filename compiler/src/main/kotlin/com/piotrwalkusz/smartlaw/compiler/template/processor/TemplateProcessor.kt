package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.Template

interface TemplateProcessor {

    fun getTemplateType(): Class<*>

    fun <T> processTemplate(template: Template<T>, context: TemplateProcessorContext): T
}