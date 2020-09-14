package com.piotrwalkusz.smartlaw.model.annotation

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.meta.MetaValue

data class Annotation(
        val annotationType: Id,
        val arguments: List<MetaValue> = listOf()
)