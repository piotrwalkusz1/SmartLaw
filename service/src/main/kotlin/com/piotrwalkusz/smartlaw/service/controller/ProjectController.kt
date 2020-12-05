package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.FromDocumentToNaturalLanguageConverter
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageDocument
import com.piotrwalkusz.smartlaw.service.controller.dto.ConvertDocumentToNaturalLanguageDto
import com.piotrwalkusz.smartlaw.service.service.DocumentService
import com.piotrwalkusz.smartlaw.service.service.FromDocumentToNaturalLanguageConverterFactory
import com.piotrwalkusz.smartlaw.service.service.ProjectService
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
}