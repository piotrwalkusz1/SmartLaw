package com.piotrwalkusz.smartlaw.core.model.meta

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate

@GenerateTemplate
data class MetaPrimitiveValue(
        val value: String
) : MetaValue