package com.piotrwalkusz.smartlaw.service.model

import com.piotrwalkusz.smartlaw.core.model.document.Document

data class DocumentWrapper(
        val id: String,
        val document: Document
)