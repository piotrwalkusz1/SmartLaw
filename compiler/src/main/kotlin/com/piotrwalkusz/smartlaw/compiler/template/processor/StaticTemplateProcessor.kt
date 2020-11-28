package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate

class StaticTemplateProcessor : TemplateProcessor<StaticTemplate<Any?>, Any?> {

    override val templateType: Class<StaticTemplate<Any?>>
        @Suppress("UNCHECKED_CAST")
        get() = StaticTemplate::class.java as Class<StaticTemplate<Any?>>

    override val resultType: Class<Any?>
        @Suppress("UNCHECKED_CAST")
        get() = Any::class.java as Class<Any?>

    override fun processTemplate(template: StaticTemplate<Any?>, context: TemplateProcessorContext): Any? {
        return template.value
    }
}