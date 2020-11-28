package com.piotrwalkusz.smartlaw.core.model.template

data class TextEngineTemplate(
        val type: String,
        val template: String
) : Template<String>