package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.FromDocumentToNaturalLanguageConverter
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.ExtendedPresentationElement
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageDocument
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement
import com.piotrwalkusz.smartlaw.service.controller.dto.ConvertDocumentToNaturalLanguageDto
import com.piotrwalkusz.smartlaw.service.controller.dto.ExtendPresentationElementsResultDto
import com.piotrwalkusz.smartlaw.service.service.FromDocumentToNaturalLanguageConverterFactory
import com.piotrwalkusz.smartlaw.service.service.RuleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/projects")
class ProjectController(
        private val fromDocumentToNaturalLanguageConverterFactory: FromDocumentToNaturalLanguageConverterFactory,
        private val ruleService: RuleService,
) {

    @PostMapping("/{projectId}/documents/convert/natural-language")
    fun convertDocumentToNaturalLanguage(@PathVariable projectId: String, @RequestBody request: ConvertDocumentToNaturalLanguageDto): NaturalLanguageDocument {
        val ruleProvider = ruleService.getRuleProviderForProject(projectId)
        val naturalLanguageConverter = fromDocumentToNaturalLanguageConverterFactory.create(ruleProvider, FromDocumentToNaturalLanguageConverter.Config(addStyleToRuleContent = true))

        return naturalLanguageConverter.convert(request.document)
    }

    @PostMapping("/{projectId}/documents/extend-presentation-elements")
    fun extendPresentationElements(@PathVariable projectId: String, @RequestBody presentationElements: List<PresentationElement>): ExtendPresentationElementsResultDto {
        val ruleProvider = ruleService.getRuleProviderForProject(projectId)
        val naturalLanguageConverter = fromDocumentToNaturalLanguageConverterFactory.create(ruleProvider, FromDocumentToNaturalLanguageConverter.Config(addStyleToRuleContent = true))
        val extendedPresentationElements = naturalLanguageConverter.extendPresentationElements(presentationElements)

        return ExtendPresentationElementsResultDto(
                extendedPresentationElements,
                Output.get().getMessages())

    }
}