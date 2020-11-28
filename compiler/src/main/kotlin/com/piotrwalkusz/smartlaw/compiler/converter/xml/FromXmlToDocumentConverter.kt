package com.piotrwalkusz.smartlaw.compiler.converter.xml

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.piotrwalkusz.smartlaw.core.model.document.Document

class FromXmlToDocumentConverter(
        private val xmlMapper: XmlMapper
) {

    fun convert(xml: String): Document {
        return xmlMapper.readValue(xml, Document::class.java)
    }
}