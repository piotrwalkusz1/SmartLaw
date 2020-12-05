package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model

data class NaturalLanguageSection(
        val title: String,
        val nestedNaturalLanguageDocumentObjects: List<NaturalLanguageDocumentObject>
) : NaturalLanguageDocumentObject