package com.piotrwalkusz.smartlaw.template

interface TemplateProcessorContext {

    fun getTemplateParameters(): Map<String, Any>
}