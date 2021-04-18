package com.piotrwalkusz.smartlaw.core.model.meta

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate

@GenerateTemplate
data class MetaMapValue(
        val values: Map<String, MetaValue>
) : MetaValue