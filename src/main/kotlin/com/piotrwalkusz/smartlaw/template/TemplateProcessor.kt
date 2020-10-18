package com.piotrwalkusz.smartlaw.template

import com.piotrwalkusz.smartlaw.model.template.Template

interface TemplateProcessor<T : Template<*>, V> {

    val templateType: Class<T>

    fun processTemplate(template: T, context: TemplateProcessorContext): V
}