package com.piotrwalkusz.smartlaw.compiler.converter.xml

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.piotrwalkusz.smartlaw.core.model.document.Document

class FromDocumentToXmlConverter(
        private val xmlMapper: XmlMapper
) {

    fun convert(document: Document): String {
        return xmlMapper.writeValueAsString(document);
    }
}