package com.piotrwalkusz.smartlaw.core.model.template

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("TextEngine")
data class TextEngineTemplate(
        val type: String,
        val template: String
) : Template<String>