package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageDocument
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageDocumentObject
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageProvision
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageSection
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.ProcessRuleContentTemplateConfig
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.document.ConvertibleToNaturalLanguage
import com.piotrwalkusz.smartlaw.core.model.presentation.IndentationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import java.util.concurrent.atomic.AtomicInteger

class FromDocumentToNaturalLanguageConverter(
        private val ruleProvider: RuleProvider,
        private val templateProcessorService: TemplateProcessorService,
        private val config: Config
) {

    data class Config(
            val addStyleToRuleContent: Boolean
    )

    fun convert(document: ConvertibleToNaturalLanguage): NaturalLanguageDocument {
        val linksByElementsIds = templateProcessorService.getLinksByElementsIds(document, ruleProvider)


        return NaturalLanguageDocument(
                title = document.name,
                objects = convertPresentationElementsToNaturalLanguageDocumentObjects(document.presentationElements, linksByElementsIds)
        )
    }

    private fun convertPresentationElementsToNaturalLanguageDocumentObjects(presentationElement: List<PresentationElement>, linksByElementsIds: Map<Id, String>): List<NaturalLanguageDocumentObject> {
        val nextIndentationIndex = AtomicInteger(1)

        return presentationElement.map { convertRuleInvocationToNaturalLanguageDocumentObject(it, linksByElementsIds, nextIndentationIndex) }
    }

    private fun convertRuleInvocationToNaturalLanguageDocumentObject(presentationElement: PresentationElement, linksByElementsIds: Map<Id, String>, nextIndentationIndex: AtomicInteger): NaturalLanguageDocumentObject {
        return when (presentationElement) {
            is RuleInvocationPresentationElement -> {
                convertRuleInvocationPresentationElementToNaturalLanguage(presentationElement, linksByElementsIds)
            }
            is IndentationPresentationElement -> {
                convertIndentationPresentationElementToNaturalLanguage(presentationElement, linksByElementsIds, nextIndentationIndex)
            }
            else -> {
                throw IllegalArgumentException("Text formatter ${presentationElement.javaClass} is not supported")
            }
        }
    }

    private fun convertIndentationPresentationElementToNaturalLanguage(presentationElement: IndentationPresentationElement, linksByElementsIds: Map<Id, String>, nextIndentationIndex: AtomicInteger): NaturalLanguageSection {
        val provisions = presentationElement.presentationElements
                .map { it as RuleInvocationPresentationElement }
                .map { convertRuleInvocationPresentationElementToNaturalLanguage(it, linksByElementsIds) }

        return NaturalLanguageSection(provisions = provisions, title = "§ " + nextIndentationIndex.getAndIncrement())
    }

    private fun convertRuleInvocationPresentationElementToNaturalLanguage(ruleInvocationPresentationElement: RuleInvocationPresentationElement, linksByElementsIds: Map<Id, String>): NaturalLanguageProvision {
        val rule = ruleProvider.getRule(ruleInvocationPresentationElement.ruleInvocation.ruleId)!!
        val processRuleContentTemplateConfig = ProcessRuleContentTemplateConfig(addStyleToContent = config.addStyleToRuleContent)
        val content = templateProcessorService.processRuleContentTemplate(rule, ruleInvocationPresentationElement.ruleInvocation.arguments, linksByElementsIds, processRuleContentTemplateConfig)

        return NaturalLanguageProvision(content)
    }
}