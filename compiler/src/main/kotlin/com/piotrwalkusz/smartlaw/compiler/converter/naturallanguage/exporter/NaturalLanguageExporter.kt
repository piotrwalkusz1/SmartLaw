package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.exporter

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageDocument

interface NaturalLanguageExporter {

    fun export(document: NaturalLanguageDocument)
}