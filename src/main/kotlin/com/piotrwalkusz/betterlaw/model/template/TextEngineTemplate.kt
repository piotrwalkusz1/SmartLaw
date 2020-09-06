package com.piotrwalkusz.betterlaw.model.template

data class TextEngineTemplate(
        val type: String,
        val template: String
) : Template<String>