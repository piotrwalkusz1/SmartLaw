package com.piotrwalkusz.smartlaw.service.config

import com.fasterxml.jackson.databind.Module
import com.piotrwalkusz.smartlaw.core.model.template.Template
import com.piotrwalkusz.smartlaw.core.serialization.SubTypeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun subTypeModule(): SubTypeModule {
        return SubTypeModule("com.piotrwalkusz.smartlaw", listOf(Template::class))
    }
}