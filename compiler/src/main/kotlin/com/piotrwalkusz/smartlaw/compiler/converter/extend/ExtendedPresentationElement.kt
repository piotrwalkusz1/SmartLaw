package com.piotrwalkusz.smartlaw.compiler.converter.extend

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageDocumentObject
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(name = "Section", value = ExtendedSectionPresentationElement::class),
        JsonSubTypes.Type(name = "RuleInvocation", value = ExtendedRuleInvocationPresentationElement::class)
)
interface ExtendedPresentationElement<T : PresentationElement, R: NaturalLanguageDocumentObject> {
    val presentationElement: T
    val naturalLanguageDocumentObject: R
}