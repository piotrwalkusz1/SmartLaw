package com.piotrwalkusz.smartlaw.template

import freemarker.template.Configuration
import freemarker.template.Template
import java.io.StringReader
import java.io.StringWriter

class FreeMarkerTextEngine : TextEngine {

    private val configuration: Configuration = Configuration()

    override val templateType: String
        get() = "FreeMarker"

    override fun processTemplate(template: String, parameters: Map<String, Any>): String {
        val output = StringWriter()
        val freeMarkerTemplate = Template(null, StringReader(template), configuration)
        freeMarkerTemplate.process(parameters, output)

        return output.toString()
    }
}