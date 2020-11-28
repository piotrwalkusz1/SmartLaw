package com.piotrwalkusz.smartlaw.core
//
//import com.piotrwalkusz.smartlaw.service.converter.FromDocumentToNaturalLanguageConverter
//import com.piotrwalkusz.smartlaw.service.converter.FromDocumentToXmlConverter
//import com.piotrwalkusz.smartlaw.service.converter.FromXmlToDocumentConverter
//import com.piotrwalkusz.smartlaw.service.converter.naturallanguage.DocxExporter
//import com.piotrwalkusz.smartlaw.core.CarSalesContractExample
//import com.piotrwalkusz.smartlaw.core.Contract
//import com.piotrwalkusz.smartlaw.service.service.provider.*
//import com.piotrwalkusz.smartlaw.template.*
//import com.piotrwalkusz.smartlaw.service.service.xml.XmlMapperProvider
//import org.koin.core.component.KoinApiExtension
//import org.koin.core.component.KoinComponent
//import org.koin.core.component.inject
//import org.koin.core.context.startKoin
//import org.koin.core.qualifier.named
//import org.koin.dsl.module
//
//@KoinApiExtension
//fun main() {
//
//    val documents = listOf(CarSalesContractExample.library)
//
//    val module = module {
//        single { XmlMapperProvider.getXmlMapper() }
//        single { FromDocumentToXmlConverter(get()) }
//        single { FromXmlToDocumentConverter(get()) }
//        single { FromDocumentToNaturalLanguageConverter(get(), getAll()) }
//        single { RuleProvider(get(), get()) }
//        single { LocalRuleBrowser(documents) as RuleBrowser }
//        single { LocalDocumentProvider(documents) as DocumentProvider }
//        single(named<StaticTemplateProcessor>()) { StaticTemplateProcessor() as TemplateProcessor<*, *> }
//        single(named<TextEngineTemplateProcessor>()) { TextEngineTemplateProcessor(getAll()) as TemplateProcessor<*, *> }
//        single { FreeMarkerTextEngine() as TextEngine }
//        single { DocxExporter() }
//    }
//
//    startKoin {
//        printLogger()
//        modules(module)
//    }
//
//    val app = App()
//    app.test()
//}
//
//@KoinApiExtension
//class App : KoinComponent {
//
//    private val fromDocumentToXmlConverter: FromDocumentToXmlConverter by inject()
//    private val fromXmlToDocumentConverter: FromXmlToDocumentConverter by inject()
//    private val fromDocumentToNaturalLanguageConverter: FromDocumentToNaturalLanguageConverter by inject()
//    private val docxExporter: DocxExporter by inject()
//
//    fun test() {
//        val originalDocument = CarSalesContractExample.contract
//        val xmlDocument = fromDocumentToXmlConverter.convert(originalDocument)
//        println(xmlDocument)
//        val document = fromXmlToDocumentConverter.convert(xmlDocument) as Contract
//        assert(document == originalDocument)
//
//        docxExporter.export(fromDocumentToNaturalLanguageConverter.convert(document))
//    }
//}
