package com.piotrwalkusz.smartlaw.compiler.template.processor.context

interface TemplateProcessorContext {

    fun getTemplateParameters(): Map<String, Any>
}