package com.piotrwalkusz.smartlaw.template

import com.piotrwalkusz.smartlaw.model.template.StaticTemplate

class StaticTemplateProcessor : TemplateProcessor<StaticTemplate<*>, Any> {

    override val templateType: Class<StaticTemplate<*>>
        get() = StaticTemplate::class.java

    override fun processTemplate(template: StaticTemplate<*>, context: TemplateProcessorContext): Any {
        return template.value!!
    }
}