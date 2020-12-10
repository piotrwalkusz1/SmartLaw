package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.exporter

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageDocument
import java.io.OutputStream

interface NaturalLanguageExporter {

    fun export(document: NaturalLanguageDocument, outputStream: OutputStream)
}