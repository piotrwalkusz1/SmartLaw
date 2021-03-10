package com.piotrwalkusz.smartlaw.core.model.template

// TODO: Remove T parameter
data class ListTemplate<T : Template<R>, R>(
        val items: List<Template<R>>
) : Template<List<R>>