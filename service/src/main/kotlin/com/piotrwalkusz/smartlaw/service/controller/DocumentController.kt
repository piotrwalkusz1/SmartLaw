package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.core.model.document.Document
import com.piotrwalkusz.smartlaw.service.service.DocumentService
import org.springframework.web.bind.annotation.*
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

    @PutMapping("/{documentId}")
    fun saveDocument(@PathVariable documentId: String, @RequestBody document: Document) {
        documentService.saveDocument(documentId, document)
    }
}