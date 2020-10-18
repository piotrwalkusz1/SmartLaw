package com.piotrwalkusz.smartlaw.converter

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.piotrwalkusz.smartlaw.model.document.Document

class FromXmlToDocumentConverter(
        private val xmlMapper: XmlMapper
) {

    fun convert(xml: String): Document {
        return xmlMapper.readValue(xml, Document::class.java)
    }
}