package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model

data class NaturalLanguageDocument(
        val title: String,
        val objects: List<NaturalLanguageDocumentObject>
)