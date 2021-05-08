package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.converter.extend.PresentationElementExtender
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.exporter.DocxExporter
import com.piotrwalkusz.smartlaw.service.controller.dto.*
import com.piotrwalkusz.smartlaw.service.example.CarSalesContractExample
import com.piotrwalkusz.smartlaw.service.model.Project
import com.piotrwalkusz.smartlaw.service.service.*
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/projects")
class ProjectController(
        private val fromDocumentToNaturalLanguageConverterFactory: FromDocumentToNaturalLanguageConverterFactory,
        private val fromContractToSmartContractConverterFactory: FromContractToSmartContractConverterFactory,
        private val fromContractToLogicRulesConverterFactory: FromContractToLogicRulesConverterFactory,
        private val presentationElementExtenderFactory: PresentationElementExtenderFactory,
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
        val externalElements = projectService.getExternalElementsInProject(projectId)
        val naturalLanguageConverter = fromDocumentToNaturalLanguageConverterFactory.create(
                ruleProvider, PresentationElementExtender.Config(addStyleToRuleContent = false, externalElements = externalElements))
        val naturalLanguageDocument = naturalLanguageConverter.convert(request.document)

        response.contentType = "application/octet-stream";
        response.setHeader("Content-Disposition", "attachment; filename=" + naturalLanguageDocument.title + ".docx");

        DocxExporter().export(naturalLanguageDocument, response.outputStream)
    }

    @PostMapping("/{projectId}/documents/convert/smart-contract")
    fun convertContractToSmartContract(@PathVariable projectId: String, @RequestBody request: ConvertContractToSmartContractDto, response: HttpServletResponse) {
        val ruleProvider = ruleService.getRuleProviderForProject(projectId)
        val externalElements = projectService.getExternalElementsInProject(projectId)
        val smartContractConverter = fromContractToSmartContractConverterFactory.create(ruleProvider)
        val smartContract = smartContractConverter.convert(request.contract, externalElements)

        response.contentType = MediaType.TEXT_PLAIN_VALUE
        response.characterEncoding = "UTF-8"
        response.setHeader("Content-Disposition", "attachment; filename=" + request.contract.name + ".txt")
        response.writer.write(smartContract)
    }

    @PostMapping("/{projectId}/documents/convert/logic-rules")
    fun convertContractToLogicRules(@PathVariable projectId: String, @RequestBody request: ConvertContractToSmartContractDto, response: HttpServletResponse) {
        val ruleProvider = ruleService.getRuleProviderForProject(projectId)
        val externalElements = projectService.getExternalElementsInProject(projectId)
        val logicRulesConverter = fromContractToLogicRulesConverterFactory.create(ruleProvider)
        val logicRules = logicRulesConverter.convert(request.contract, externalElements)

        response.contentType = MediaType.TEXT_PLAIN_VALUE
        response.characterEncoding = "UTF-8"
        response.setHeader("Content-Disposition", "attachment; filename=" + request.contract.name + "-rules.txt")
        response.writer.write(logicRules)
    }

    @PostMapping("/{projectId}/documents/extend-presentation-elements")
    fun extendPresentationElements(@PathVariable projectId: String, @RequestBody extendPresentationElementsDto: ExtendPresentationElementsDto): ExtendPresentationElementsResultDto {
        Output.get().clearMessages()
        val ruleProvider = ruleService.getRuleProviderForProject(projectId)
        val externalElements = projectService.getExternalElementsInProject(projectId)
        val presentationElementExtender = presentationElementExtenderFactory.create(ruleProvider, PresentationElementExtender.Config(
                addStyleToRuleContent = true,
                validateElements = true,
                externalElements = externalElements))
        val extendedPresentationElements = presentationElementExtender.extendPresentationElements(
                extendPresentationElementsDto.allPresentationElements,
                extendPresentationElementsDto.presentationElementsToExtend
        )

        return ExtendPresentationElementsResultDto(
                extendedPresentationElements,
                Output.get().getMessages())
    }
}