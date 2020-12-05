package com.piotrwalkusz.smartlaw.service.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MongoConfig {

    companion object {
        const val DATABASE = "default";
    }

    @Bean
    fun mongoClient(): MongoClient {
        return KMongo.createClient()
    }

    @Bean
    fun mongoDatabase(mongoClient: MongoClient): MongoDatabase {
        return mongoClient.getDatabase(DATABASE)
    }
}