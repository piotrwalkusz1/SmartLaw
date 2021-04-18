package com.piotrwalkusz.smartlaw.service.dao;

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.piotrwalkusz.smartlaw.service.model.DocumentModule
import com.piotrwalkusz.smartlaw.service.model.Project
import org.litote.kmongo.`in`
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.projection
import org.springframework.stereotype.Service

@Service
class ModuleDao(private val mongoDatabase: MongoDatabase) {

    fun getDocumentsIdsByModulesIds(modulesIds: List<String>): List<String> {
        if (modulesIds.isEmpty()) {
            return listOf()
        }

        return getCollection().find(DocumentModule::id `in` modulesIds)
                .flatMap { it.documentsIds }
    }

    fun getModuleById(moduleId: String): DocumentModule? {
        return getCollection().findOne(DocumentModule::id eq moduleId)
    }

    fun insertModule(module: DocumentModule) {
        getCollection().insertOne(module)
    }

    private fun getCollection(): MongoCollection<DocumentModule> {
        return mongoDatabase.getCollection("modules", DocumentModule::class.java)
    }
}
