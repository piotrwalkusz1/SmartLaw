package com.piotrwalkusz.smartlaw.template;

interface TextEngine {

    val templateType: String

    fun processTemplate(template: String, parameters: Map<String, Any>): String
}
