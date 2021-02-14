package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.GroovyTemplate
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import groovy.lang.Binding
import groovy.lang.GroovyShell

class GroovyTemplateProcessor : TemplateProcessor<GroovyTemplate<Any>, Any> {

    override val templateType: Class<GroovyTemplate<Any>>
        @Suppress("UNCHECKED_CAST")
        get() = GroovyTemplate::class.java as Class<GroovyTemplate<Any>>

    override val resultType: Class<Any>
        get() = Any::class.java

    override fun processTemplate(template: GroovyTemplate<Any>, context: TemplateProcessorContext): Any {
        val binding = Binding()
        context.getTemplateParameters().forEach { (key, value) -> binding.setVariable(key, value) }
        val shell = GroovyShell(binding)

        // TODO: Handle exceptions
        return shell.evaluate(template.script)
    }
}