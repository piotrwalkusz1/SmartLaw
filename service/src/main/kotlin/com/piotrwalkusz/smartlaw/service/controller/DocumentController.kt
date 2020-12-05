package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.core.model.document.Document
import com.piotrwalkusz.smartlaw.service.service.DocumentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/documents")
class DocumentController(
        private val documentService: DocumentService
) {

    @GetMapping("/{documentId}")
    fun getDocument(@PathVariable documentId: String): Document {
        return documentService.getDocument(documentId).document
    }
}