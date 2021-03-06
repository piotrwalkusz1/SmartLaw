package com.piotrwalkusz.smartlaw.service.dao

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInterface
import com.piotrwalkusz.smartlaw.service.model.DocumentWrapper
import org.bson.conversions.Bson
import org.litote.kmongo.`in`
import org.litote.kmongo.and
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.springframework.stereotype.Service

@Service
class DocumentDao(private val mongoDatabase: MongoDatabase) {

    fun getDocumentById(documentId: String): DocumentWrapper? {
        return getCollection().findOne(DocumentWrapper::id eq documentId)
    }

    fun getLegalActs(documentsIds: List<String>): List<DocumentWrapper> {
        return getCollection()
                .find(and(DocumentWrapper::id `in` documentsIds, Filters.eq("document.documentType", "LegalAct")))
                .toList()
    }

    fun getRuleById(ruleId: Id, documentsIds: List<String>): Rule? {
        return getRulesByFilter(Rule::id eq ruleId, documentsIds, 1).getOrNull(0);
    }

    fun getRulesByFilter(filter: Bson?, documentsIds: List<String>, limit: Int, sortBy: Bson? = null): List<Rule> {
        return getCollection().aggregate(
                listOfNotNull(
                        Aggregates.match(DocumentWrapper::id `in` documentsIds),
                        Aggregates.unwind("\$document.rules"),
                        Aggregates.replaceRoot("\$document.rules"),
                        filter?.let { Aggregates.match(it) },
                        sortBy?.let { Aggregates.sort(it) },
                        Aggregates.limit(limit)
                ),
                Rule::class.java
        ).toList();
    }

    fun getRulesInterfacesByFilter(filter: Bson?, documentsIds: List<String>, limit: Int? = null, sortBy: Bson? = null): List<RuleInterface> {
        return getCollection().aggregate(
                listOfNotNull(
                        Aggregates.match(DocumentWrapper::id `in` documentsIds),
                        Aggregates.unwind("\$document.rulesInterfaces"),
                        Aggregates.replaceRoot("\$document.rulesInterfaces"),
                        filter?.let { Aggregates.match(it) },
                        sortBy?.let { Aggregates.sort(it) },
                        limit?.let { Aggregates.limit(it) }
                ),
                RuleInterface::class.java
        ).toList();
    }

    fun insertDocument(document: DocumentWrapper) {
        getCollection().insertOne(document)
    }

    fun saveDocument(document: DocumentWrapper) {
        getCollection().replaceOne(DocumentWrapper::id eq document.id, document)
    }

    private fun getCollection(): MongoCollection<DocumentWrapper> {
        return mongoDatabase.getCollection("documents", DocumentWrapper::class.java)
    }
}