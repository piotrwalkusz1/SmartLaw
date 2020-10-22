package com.piotrwalkusz.smartlaw.converter.naturallanguage.model

data class NaturalLanguageDocument(
        val title: String,
        val objects: List<NaturalLanguageDocumentObject>
)