package com.piotrwalkusz.smartlaw.service.service;

import com.piotrwalkusz.smartlaw.core.model.document.Document
import com.piotrwalkusz.smartlaw.service.dao.DocumentDao
import com.piotrwalkusz.smartlaw.service.dao.common.Sequence
import com.piotrwalkusz.smartlaw.service.model.DocumentWrapper
import org.springframework.stereotype.Service

@Service
class DocumentService(private val documentDao: DocumentDao, private val sequence: Sequence) {

    fun getDocument(documentId: String): DocumentWrapper {
        return documentDao.getDocumentById(documentId)
                ?: throw IllegalArgumentException("No document with id $documentId")
    }

    fun createDocument(document: Document): DocumentWrapper {
        val documentWrapper = DocumentWrapper(
                id = sequence.getNext(DocumentWrapper::class.java),
                document = document)
        documentDao.insertDocument(documentWrapper)

        return documentWrapper
    }
}
