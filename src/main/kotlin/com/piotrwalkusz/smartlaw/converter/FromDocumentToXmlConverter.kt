package com.piotrwalkusz.smartlaw.converter

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.piotrwalkusz.smartlaw.model.document.Document

class FromDocumentToXmlConverter(
        private val xmlMapper: XmlMapper
) {

    fun convert(document: Document): String {
        return xmlMapper.writeValueAsString(document);
    }
}