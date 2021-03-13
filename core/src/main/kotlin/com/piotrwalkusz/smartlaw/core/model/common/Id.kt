package com.piotrwalkusz.smartlaw.core.model.common

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate

@GenerateTemplate
data class Id(
        val id: String,
        val namespace: String? = null
)