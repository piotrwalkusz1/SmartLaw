package com.piotrwalkusz.smartlaw.model.template

data class TextEngineTemplate(
        val type: String,
        val template: String
) : Template<String>