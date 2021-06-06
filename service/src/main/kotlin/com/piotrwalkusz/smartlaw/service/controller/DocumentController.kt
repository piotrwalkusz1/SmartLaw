package com.piotrwalkusz.smartlaw.service.controller

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.piotrwalkusz.smartlaw.core.model.document.Document
import com.piotrwalkusz.smartlaw.service.model.DocumentWrapper
import com.piotrwalkusz.smartlaw.service.service.DocumentService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.PUT

@RestController
@RequestMapping("/documents")
class DocumentController(
        private val documentService: DocumentService
) {

    @GetMapping("/{documentId}")
    fun getDocument(@PathVariable documentId: String): Document {
        return documentService.getDocument(documentId).document
    }

    @GetMapping("/{documentId}/xml")
    fun getDocumentAsXml(@PathVariable documentId: String, response: HttpServletResponse) {
        val document = documentService.getDocument(documentId)
        val xml = XmlMapper().writeValueAsString(document)

        response.contentType = MediaType.TEXT_PLAIN_VALUE
        response.characterEncoding = "UTF-8"
        response.setHeader("Content-Disposition", "attachment; filename=Document-" + documentId + ".txt")
        response.writer.write(xml)
    }

    @PutMapping("/{documentId}")
    fun saveDocument(@PathVariable documentId: String, @RequestBody document: Document) {
        documentService.saveDocument(documentId, document)
    }
}