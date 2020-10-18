package com.piotrwalkusz.smartlaw.model.template

data class StaticTemplate<T>(
        val value: T
) : Template<T>