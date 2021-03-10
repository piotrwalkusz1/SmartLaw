package com.piotrwalkusz.smartlaw.core.model.template

interface ComplexTemplate<T> : Template<T> {

    interface TemplateProcessor {
        fun <Q> processTemplate(template: Template<Q>): Q
    }

    fun getValue(templateProcessor: TemplateProcessor): T
}