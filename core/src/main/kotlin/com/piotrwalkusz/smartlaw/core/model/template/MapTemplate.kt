package com.piotrwalkusz.smartlaw.core.model.template

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("MapTemplate")
data class MapTemplate<T>(
        val items: Map<String, Template<T>>
) : Template<Map<String, T>>