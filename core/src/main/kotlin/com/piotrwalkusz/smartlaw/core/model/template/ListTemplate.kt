package com.piotrwalkusz.smartlaw.core.model.template

import com.fasterxml.jackson.annotation.JsonTypeName

// TODO: Remove T parameter
@JsonTypeName("ListTemplate")
data class ListTemplate<T : Template<R>, R>(
        val items: List<Template<R>>
) : Template<List<R>>