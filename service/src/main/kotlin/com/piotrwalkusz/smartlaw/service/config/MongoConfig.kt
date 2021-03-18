package com.piotrwalkusz.smartlaw.service.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import com.piotrwalkusz.smartlaw.core.serialization.SubTypeModule
import org.litote.kmongo.KMongo
import org.litote.kmongo.util.KMongoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct


@Configuration
class MongoConfig(private val subTypeModule: SubTypeModule) {

    companion object {
        const val DATABASE = "default";
    }

    @PostConstruct
    fun init() {
        KMongoConfiguration.registerBsonModule(subTypeModule)
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