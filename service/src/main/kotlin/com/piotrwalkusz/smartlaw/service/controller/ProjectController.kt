package com.piotrwalkusz.smartlaw.service.controller

import com.mongodb.client.MongoDatabase
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.exporter.DocxExporter
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.core.example.CarSalesContractExample
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.document.ConvertibleToNaturalLanguage
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.service.model.DocumentWrapper
import com.piotrwalkusz.smartlaw.service.service.DocumentService
import com.piotrwalkusz.smartlaw.service.service.FromDocumentToNaturalLanguageConverterFactory
import com.piotrwalkusz.smartlaw.service.service.ProjectService
import com.piotrwalkusz.smartlaw.service.service.RuleService
import org.bson.types.ObjectId
import org.litote.kmongo.util.idValue
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/projects")
class ProjectController(
        private val fromDocumentToNaturalLanguageConverterFactory: FromDocumentToNaturalLanguageConverterFactory,
        private val projectService: ProjectService,
        private val ruleService: RuleService,
        private val documentService: DocumentService
) {

    @PostMapping("/{projectId}/documents/{documentId}/convert/natural-language")
    fun convertDocumentToNaturalLanguage(@PathVariable projectId: String, @PathVariable documentId: String) {
        val id1 = documentService.createDocument(CarSalesContractExample.library).id
        val id2 = documentService.createDocument(CarSalesContractExample.contract).id
        val project = projectService.createProject("test", listOf(id1, id2))

        val documentsIds = projectService.getDocumentsIdsInProjectAndModules(projectId)
        val ruleProvider = object : RuleProvider {
            override fun getRule(ruleId: Id): Rule? {
                return ruleService.getRuleById(ruleId, documentsIds)
            }
        }
        val naturalLanguageConverter = fromDocumentToNaturalLanguageConverterFactory.create(ruleProvider)
        val document = documentService.getDocument(documentId).document
        val naturalLanguageDocument = naturalLanguageConverter.convert(document as ConvertibleToNaturalLanguage)

        DocxExporter().export(naturalLanguageDocument)
    }
}