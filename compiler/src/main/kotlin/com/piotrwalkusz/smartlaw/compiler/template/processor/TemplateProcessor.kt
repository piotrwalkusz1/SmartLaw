package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.Template

interface TemplateProcessor<T : Template<R>, R> {

    val templateType: Class<T>

    val resultType: Class<R>

    fun processTemplate(template: T, context: TemplateProcessorContext): R
}