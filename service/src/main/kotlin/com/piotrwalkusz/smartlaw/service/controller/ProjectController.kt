package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.FromDocumentToNaturalLanguageConverter
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.exporter.DocxExporter
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.ExtendedPresentationElement
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageDocument
import com.piotrwalkusz.smartlaw.core.example.CarSalesContractExample
import com.piotrwalkusz.smartlaw.core.model.document.Library
import com.piotrwalkusz.smartlaw.core.model.presentation.PresentationElement
import com.piotrwalkusz.smartlaw.service.controller.dto.*
import com.piotrwalkusz.smartlaw.service.model.Project
import com.piotrwalkusz.smartlaw.service.service.DocumentService
import com.piotrwalkusz.smartlaw.service.service.FromDocumentToNaturalLanguageConverterFactory
import com.piotrwalkusz.smartlaw.service.service.ProjectService
import com.piotrwalkusz.smartlaw.service.service.RuleService
import org.springframework.web.bind.annotation.*
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

import org.springframework.http.ResponseEntity

import java.io.FileInputStream
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/projects")
class ProjectController(
        private val fromDocumentToNaturalLanguageConverterFactory: FromDocumentToNaturalLanguageConverterFactory,
        private val ruleService: RuleService,
        private val projectService: ProjectService,
        private val documentService: DocumentService
) {

    @GetMapping("/{projectId}")
    fun getProject(@PathVariable projectId: String): Project {
        return projectService.getProject(projectId)
    }

    @PostMapping
    fun createProject(@RequestBody createProjectDto: CreateProjectDto): Project {
        val contractId = documentService.createDocument(CarSalesContractExample.contract).id
        val libraryId = documentService.createDocument(CarSalesContractExample.library).id

        return projectService.createProject(
                name = createProjectDto.name,
                documentsIds = listOf(contractId, libraryId))
    }

    @PostMapping("/search")
    fun searchProjects(@RequestBody searchProjectsDto: SearchProjectsDto): List<Project> {
        return projectService.searchProjects(searchProjectsDto)
    }

    @PostMapping("/{projectId}/documents/convert/natural-language")
    fun convertDocumentToNaturalLanguage(@PathVariable projectId: String, @RequestBody request: ConvertDocumentToNaturalLanguageDto, response: HttpServletResponse) {
        val ruleProvider = ruleService.getRuleProviderForProject(projectId)
        val naturalLanguageConverter = fromDocumentToNaturalLanguageConverterFactory.create(ruleProvider, FromDocumentToNaturalLanguageConverter.Config(addStyleToRuleContent = false))
        val naturalLanguageDocument = naturalLanguageConverter.convert(request.document)

        response.contentType = "application/octet-stream";
        response.setHeader("Content-Disposition", "attachment; filename=" + naturalLanguageDocument.title + ".docx");

        DocxExporter().export(naturalLanguageDocument, response.outputStream)
    }

    @PostMapping("/{projectId}/documents/extend-presentation-elements")
    fun extendPresentationElements(@PathVariable projectId: String, @RequestBody extendPresentationElementsDto: ExtendPresentationElementsDto): ExtendPresentationElementsResultDto {
        Output.get().clearMessages()
        val ruleProvider = ruleService.getRuleProviderForProject(projectId)
        val naturalLanguageConverter = fromDocumentToNaturalLanguageConverterFactory.create(ruleProvider, FromDocumentToNaturalLanguageConverter.Config(addStyleToRuleContent = true))
        val extendedPresentationElements = naturalLanguageConverter.extendPresentationElements(
                extendPresentationElementsDto.allPresentationElements,
                extendPresentationElementsDto.presentationElementsToExtend
        )

        return ExtendPresentationElementsResultDto(
                extendedPresentationElements,
                Output.get().getMessages())
    }
}