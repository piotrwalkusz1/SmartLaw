package com.piotrwalkusz.smartlaw.compiler.template.processor.textengine

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import freemarker.template.Configuration
import freemarker.template.Template
import java.io.StringReader
import java.io.StringWriter

class FreeMarkerTextEngine : TextEngine {

    companion object {
        const val TEMPLATE_TYPE = "FreeMarker"
    }

    private val configuration: Configuration = Configuration()

    override val templateType: String
        get() = TEMPLATE_TYPE

    override fun processTemplate(template: String, parameters: Map<String, Any>): String {
        val output = StringWriter()
        val freeMarkerTemplate = Template(null, StringReader(template), configuration)
        // TODO
        freeMarkerTemplate.setTemplateExceptionHandler { te, env, out -> Output.get().addError("Error in template") }
        freeMarkerTemplate.process(parameters, output)

        return output.toString()
    }
}