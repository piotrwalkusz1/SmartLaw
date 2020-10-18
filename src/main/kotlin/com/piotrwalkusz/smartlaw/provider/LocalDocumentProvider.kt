package com.piotrwalkusz.smartlaw.provider

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.document.Document

class LocalDocumentProvider(documents: List<Document>) : DocumentProvider {

    private val documents: Map<Id, Document> = documents.associateBy { it.id }

    override fun getDocument(documentId: Id): Document? {
        return documents.get(documentId)
    }
}