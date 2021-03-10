package com.piotrwalkusz.smartlaw.service.config

import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleContentTemplateProcessor
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.RuleElementsTemplateProcessor
import com.piotrwalkusz.smartlaw.compiler.validator.ValidatorService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CompilerConfig {

    @Bean
    fun templateProcessorService(): TemplateProcessorService {
        return TemplateProcessorService()
    }

    @Bean
    fun validatorService(): ValidatorService {
        return ValidatorService()
    }

    @Bean
    fun ruleContentTemplateProcessor(
            ruleElementsTemplateProcessor: RuleElementsTemplateProcessor,
            templateProcessorService: TemplateProcessorService
    ): RuleContentTemplateProcessor {
        return RuleContentTemplateProcessor(ruleElementsTemplateProcessor, templateProcessorService)
    }

    @Bean
    fun ruleElementsTemplateProcessor(templateProcessorService: TemplateProcessorService): RuleElementsTemplateProcessor {
        return RuleElementsTemplateProcessor(templateProcessorService)
    }
}