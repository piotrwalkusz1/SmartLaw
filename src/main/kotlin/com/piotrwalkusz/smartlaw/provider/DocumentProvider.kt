package com.piotrwalkusz.smartlaw.provider

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.document.Document

interface DocumentProvider {

    fun getDocument(documentId: Id): Document?
}