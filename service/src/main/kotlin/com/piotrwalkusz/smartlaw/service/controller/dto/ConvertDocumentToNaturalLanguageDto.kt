package com.piotrwalkusz.smartlaw.service.controller.dto

import com.piotrwalkusz.smartlaw.core.model.document.ConvertibleToNaturalLanguage

data class ConvertDocumentToNaturalLanguageDto(
        val document: ConvertibleToNaturalLanguage
)