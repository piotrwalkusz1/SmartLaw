package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.FromDocumentToNaturalLanguageConverter
import com.piotrwalkusz.smartlaw.compiler.template.processor.ProcessRuleContentTemplateConfig
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.core.model.document.ConvertibleToNaturalLanguage
import com.piotrwalkusz.smartlaw.service.controller.dto.ProcessTemplateDto
import com.piotrwalkusz.smartlaw.service.service.DocumentService
import com.piotrwalkusz.smartlaw.service.service.RuleService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/templates")
class TemplateController(
        private val ruleService: RuleService,
        private val templateProcessorService: TemplateProcessorService,
        private val documentService: DocumentService
) {

    @PostMapping("/process")
    fun processTemplate(@RequestBody processTemplateDto: ProcessTemplateDto): String {
        val ruleProvider = ruleService.getRuleProviderForProject(processTemplateDto.projectId)
        val rule = ruleProvider.getRule(processTemplateDto.ruleId)!!
        val document = documentService.getDocument(processTemplateDto.documentId).document as ConvertibleToNaturalLanguage
        val linksByElementsIds = templateProcessorService.getLinksByElementsIds(document, ruleProvider)

        return templateProcessorService.processRuleContentTemplate(rule, processTemplateDto.arguments, linksByElementsIds, ProcessRuleContentTemplateConfig())
    }
}