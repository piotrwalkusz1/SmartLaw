package com.piotrwalkusz.smartlaw.core.model.template

data class StaticTemplate<T>(
        val value: T
) : Template<T>