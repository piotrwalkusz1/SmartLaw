package com.piotrwalkusz.betterlaw.model.template

data class StaticTemplate<T>(
        val value: T
) : Template<T>