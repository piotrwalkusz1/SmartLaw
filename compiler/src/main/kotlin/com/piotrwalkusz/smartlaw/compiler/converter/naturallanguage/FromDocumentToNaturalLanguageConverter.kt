package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage

import com.piotrwalkusz.smartlaw.compiler.converter.extend.PresentationElementExtender
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageDocument
import com.piotrwalkusz.smartlaw.core.model.document.ConvertibleToNaturalLanguage

class FromDocumentToNaturalLanguageConverter(
        private val presentationElementExtender: PresentationElementExtender
) {

    fun convert(document: ConvertibleToNaturalLanguage): NaturalLanguageDocument {
        val extendedPresentationElements = presentationElementExtender.extendPresentationElements(document.presentationElements);

        return NaturalLanguageDocument(
                title = document.name,
                objects = extendedPresentationElements.map { it.naturalLanguageDocumentObject }
        )
    }
}