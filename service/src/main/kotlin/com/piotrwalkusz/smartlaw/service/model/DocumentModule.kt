package com.piotrwalkusz.smartlaw.service.model

data class DocumentModule(
        val id: String,
        val name: String,
        val documentsIds: List<String>
) {

    companion object {
        const val DOCUMENTS_IDS_FIELD = "documentsIds"
    }
}