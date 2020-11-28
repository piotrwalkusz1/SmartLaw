package com.piotrwalkusz.smartlaw.compiler.template.processor.textengine;

interface TextEngine {

    companion object {
        val DEFAULT_TEXT_ENGINES = listOf<TextEngine>(FreeMarkerTextEngine())
    }

    val templateType: String

    fun processTemplate(template: String, parameters: Map<String, Any>): String
}
