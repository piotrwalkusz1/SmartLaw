package com.piotrwalkusz.smartlaw.core.model.template

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("Static")
data class StaticTemplate<T>(
        val value: T
) : Template<T>