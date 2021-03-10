package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.GroovyTemplate
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.Template
import groovy.lang.Binding
import groovy.lang.GroovyShell

class GroovyTemplateProcessor : TemplateProcessor {

    override fun getTemplateType(): Class<*> {
        return GroovyTemplate::class.java
    }

    override fun <T> processTemplate(template: Template<T>, context: TemplateProcessorContext): T {
        if (template !is GroovyTemplate) {
            throw IllegalArgumentException("Template must be instance of GroovyTemplate class")
        }

        val binding = Binding()
        context.templateParameters.forEach { (key, value) -> binding.setVariable(key, value) }
        val shell = GroovyShell(binding)

        // TODO: Handle exceptions
        @Suppress("UNCHECKED_CAST")
        return shell.evaluate(template.script) as T
    }
}