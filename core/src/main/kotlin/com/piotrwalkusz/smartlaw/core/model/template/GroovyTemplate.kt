package com.piotrwalkusz.smartlaw.core.model.template

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("Groovy")
data class GroovyTemplate<T>(
        val script: String
) : Template<T>