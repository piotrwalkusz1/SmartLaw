package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model

import com.piotrwalkusz.smartlaw.core.model.presentation.SectionPresentationElement

data class ExtendedSectionPresentationElement(
        override val presentationElement: SectionPresentationElement,
        val title: String,
        val nestedExtendedPresentationElements: List<ExtendedPresentationElement<*, *>>
) : ExtendedPresentationElement<SectionPresentationElement, NaturalLanguageSection> {
    override val naturalLanguageDocumentObject: NaturalLanguageSection
        get() {
            return NaturalLanguageSection(title, nestedExtendedPresentationElements.map { it.naturalLanguageDocumentObject })
        }
}