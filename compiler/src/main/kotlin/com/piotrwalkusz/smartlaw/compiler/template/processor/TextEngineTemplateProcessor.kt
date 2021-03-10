package com.piotrwalkusz.smartlaw.compiler.template.processor

import com.piotrwalkusz.smartlaw.compiler.template.processor.context.TemplateProcessorContext
import com.piotrwalkusz.smartlaw.compiler.template.processor.textengine.TextEngine
import com.piotrwalkusz.smartlaw.core.model.template.Template
import com.piotrwalkusz.smartlaw.core.model.template.TextEngineTemplate

class TextEngineTemplateProcessor(
        private val textEngines: List<TextEngine> = TextEngine.DEFAULT_TEXT_ENGINES
) : TemplateProcessor {

    override fun getTemplateType(): Class<*> {
        return TextEngineTemplate::class.java
    }

    override fun <T> processTemplate(template: Template<T>, context: TemplateProcessorContext): T {
        if (template !is TextEngineTemplate) {
            throw IllegalArgumentException("Template must be instance of TextEngineTemplate class")
        }

        val parameters = context.templateParameters

        @Suppress("UNCHECKED_CAST")
        return getTextEngine(template).processTemplate(template.template, parameters) as T
    }

    private fun getTextEngine(template: TextEngineTemplate): TextEngine {
        return textEngines.find { textEngine -> textEngine.templateType == template.type }
                ?: throw IllegalArgumentException("Cannot find text engine to template with type ${template.type}")
    }
}