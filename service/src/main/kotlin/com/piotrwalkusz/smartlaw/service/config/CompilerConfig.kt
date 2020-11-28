package com.piotrwalkusz.smartlaw.service.config

import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CompilerConfig {

    @Bean
    fun templateProcessorService(): TemplateProcessorService {
        return TemplateProcessorService()
    }
}