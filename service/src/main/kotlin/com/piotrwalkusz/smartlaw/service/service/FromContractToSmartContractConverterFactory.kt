package com.piotrwalkusz.smartlaw.service.service

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.FromDocumentToNaturalLanguageConverter
import com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.FromContractToSmartContractConverter
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.compiler.validator.ValidatorService
import org.springframework.stereotype.Service

@Service
class FromContractToSmartContractConverterFactory {

    fun create(): FromContractToSmartContractConverter {
        return FromContractToSmartContractConverter()
    }
}