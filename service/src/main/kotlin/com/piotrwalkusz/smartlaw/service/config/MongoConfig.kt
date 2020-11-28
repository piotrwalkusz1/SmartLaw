package com.piotrwalkusz.smartlaw.service.config

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MongoConfig {

    @Bean
    fun mongoDatabase(): MongoDatabase {
        val client = KMongo.createClient()

        return client.getDatabase("default")
    }
}