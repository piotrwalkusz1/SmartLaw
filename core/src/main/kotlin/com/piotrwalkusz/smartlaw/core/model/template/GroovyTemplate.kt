package com.piotrwalkusz.smartlaw.core.model.template

data class GroovyTemplate<T>(
        val script: String
) : Template<T>