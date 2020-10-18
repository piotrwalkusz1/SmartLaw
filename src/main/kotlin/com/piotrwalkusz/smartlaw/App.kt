package com.piotrwalkusz.smartlaw

import com.piotrwalkusz.smartlaw.converter.FromDocumentToNaturalLanguageConverter
import com.piotrwalkusz.smartlaw.converter.FromDocumentToXmlConverter
import com.piotrwalkusz.smartlaw.converter.FromXmlToDocumentConverter
import com.piotrwalkusz.smartlaw.example.CarSalesContractExample
import com.piotrwalkusz.smartlaw.model.document.Contract
import com.piotrwalkusz.smartlaw.provider.*
import com.piotrwalkusz.smartlaw.template.*
import com.piotrwalkusz.smartlaw.xml.XmlMapperProvider
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.File

@KoinApiExtension
fun main() {

    val documents = listOf(CarSalesContractExample.contract, CarSalesContractExample.library)

    val module = module {
        single { XmlMapperProvider.getXmlMapper() }
        single { FromDocumentToXmlConverter(get()) }
        single { FromXmlToDocumentConverter(get()) }
        single { FromDocumentToNaturalLanguageConverter(get(), getAll()) }
        single { RuleProvider(get(), get()) }
        single { LocalRuleBrowser(documents) as RuleBrowser }
        single { LocalDocumentProvider(documents) as DocumentProvider }
        single(named<StaticTemplateProcessor>()) { StaticTemplateProcessor() as TemplateProcessor<*, *> }
        single(named<TextEngineTemplateProcessor>()) { TextEngineTemplateProcessor(getAll()) as TemplateProcessor<*, *> }
        single { FreeMarkerTextEngine() as TextEngine }
    }

    startKoin {
        printLogger()
        modules(module)
    }

    val app = App()
    app.test()
}

@KoinApiExtension
class App : KoinComponent {

    private val fromDocumentToXmlConverter: FromDocumentToXmlConverter by inject()
    private val fromXmlToDocumentConverter: FromXmlToDocumentConverter by inject()
    private val fromDocumentToNaturalLanguageConverter: FromDocumentToNaturalLanguageConverter by inject()

    fun test() {
        val originalDocument = CarSalesContractExample.contract
        val xmlDocument = fromDocumentToXmlConverter.convert(originalDocument)
        println(xmlDocument)
        val document = fromXmlToDocumentConverter.convert(xmlDocument) as Contract
        assert(document == originalDocument)

        val nl = fromDocumentToNaturalLanguageConverter.convert(document)
        val inputFile = File.createTempFile("tmp", ".md")
        inputFile.writeText(nl)
        val outputFile = File("output.docx")
        println(outputFile.absoluteFile)
        val processBuilder = ProcessBuilder("pandoc",
                "--from", "markdown",
                "--to", "docx",
                "--output", outputFile.absolutePath,
                "--css", "/home/piotr/Projects/SmartLaw/test.css",
                inputFile.absolutePath)
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT)
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT)
        processBuilder.start().waitFor()
        inputFile.deleteOnExit()
    }
}
