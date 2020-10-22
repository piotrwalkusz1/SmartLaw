package com.piotrwalkusz.smartlaw.template

import com.piotrwalkusz.smartlaw.model.template.TextEngineTemplate

class TextEngineTemplateProcessor(private val textEngines: List<TextEngine>) : TemplateProcessor<TextEngineTemplate, String> {

    override val templateType: Class<TextEngineTemplate>
        get() = TextEngineTemplate::class.java

    override fun processTemplate(template: TextEngineTemplate, context: TemplateProcessorContext): String {
        val parameters = context.getTemplateParameters()

        return getTextEngine(template).processTemplate(template.template, parameters)
    }

    private fun getTextEngine(template: TextEngineTemplate): TextEngine {
        return textEngines.find { textEngine -> textEngine.templateType == template.type }
                ?: throw IllegalArgumentException("Cannot find text engine to template with type ${template.type}")
    }
}