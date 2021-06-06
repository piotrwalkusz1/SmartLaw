package com.piotrwalkusz.smartlaw.service.benchmark

import com.piotrwalkusz.smartlaw.compiler.converter.extend.PresentationElementExtender
import com.piotrwalkusz.smartlaw.core.model.document.Contract
import com.piotrwalkusz.smartlaw.service.service.*
import org.junit.jupiter.api.Test
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.Options
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.TimeUnit


@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class SmartLawBenchmark {

    @Autowired
    fun setRuleService(ruleService: RuleService) {
        Companion.ruleService = ruleService
    }

    @Autowired
    fun setProjectService(projectService: ProjectService) {
        Companion.projectService = projectService
    }

    @Autowired
    fun setFromContractToSmartContractConverterFactory(fromContractToSmartContractConverterFactory: FromContractToSmartContractConverterFactory) {
        Companion.fromContractToSmartContractConverterFactory = fromContractToSmartContractConverterFactory
    }

    @Autowired
    fun setFromDocumentToNaturalLanguageConverterFactory(fromDocumentToNaturalLanguageConverterFactory: FromDocumentToNaturalLanguageConverterFactory) {
        Companion.fromDocumentToNaturalLanguageConverterFactory = fromDocumentToNaturalLanguageConverterFactory
    }


    @Autowired
    fun setDocumentService(documentService: DocumentService) {
        Companion.documentService = documentService
    }

    @Test
    fun runBenchmarks() {
        val opts: Options = OptionsBuilder()
                .include("\\." + this.javaClass.simpleName + "\\.")
                .warmupIterations(3)
                .measurementIterations(10)
                .forks(0)
                .threads(1)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .jvmArgs("-server")
                .build()
        Runner(opts).run()
    }

//    @Benchmark
//    fun smartContractGenerationBenchmark(parameters: Parameters) {
//        parameters.smartContractConverter.convert(parameters.contract, parameters.externalElements)
//    }

    @Benchmark
    fun traditionalContractGenerationBenchmark(parameters: Parameters) {
        parameters.naturalLanguageConverter.convert(parameters.contract)
    }

    @State(value = Scope.Benchmark)
    open class Parameters {
        val projectId = "4"
        val ruleProvider = ruleService.getRuleProviderForProject(projectId)
        val externalElements = projectService.getExternalElementsInProject(projectId)
        val smartContractConverter = fromContractToSmartContractConverterFactory.create(ruleProvider)
        val naturalLanguageConverter = fromDocumentToNaturalLanguageConverterFactory.create(
                ruleProvider, PresentationElementExtender.Config(addStyleToRuleContent = false, externalElements = externalElements))
        var contract: Contract

        init {
            val copyContract = documentService.getDocument("7").document as Contract
            val presentationElements = copyContract.presentationElements
            contract = copyContract.copy(
                    presentationElements = (0..0).map { presentationElements }.flatten()
            )
        }
    }

    companion object {
        private lateinit var ruleService: RuleService
        private lateinit var projectService: ProjectService
        private lateinit var fromContractToSmartContractConverterFactory: FromContractToSmartContractConverterFactory
        private lateinit var fromDocumentToNaturalLanguageConverterFactory: FromDocumentToNaturalLanguageConverterFactory
        private lateinit var documentService: DocumentService
    }
}
