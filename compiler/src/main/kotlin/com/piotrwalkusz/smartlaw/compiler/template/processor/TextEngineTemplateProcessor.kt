package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.compiler.template.processor.textengine.TextEngine
import com.piotrwalkusz.smartlaw.core.model.template.TextEngineTemplate

class TextEngineTemplateProcessor(
        private val textEngines: List<TextEngine> = TextEngine.DEFAULT_TEXT_ENGINES
) : TemplateProcessor<TextEngineTemplate, String> {

    override val templateType: Class<TextEngineTemplate>
        get() = TextEngineTemplate::class.java

    override val resultType: Class<String>
        get() = String::class.java

    override fun processTemplate(template: TextEngineTemplate, context: TemplateProcessorContext): String {
        val parameters = context.getTemplateParameters()

        return getTextEngine(template).processTemplate(template.template, parameters)
    }

    private fun getTextEngine(template: TextEngineTemplate): TextEngine {
        return textEngines.find { textEngine -> textEngine.templateType == template.type }
                ?: throw IllegalArgumentException("Cannot find text engine to template with type ${template.type}")
    }
}