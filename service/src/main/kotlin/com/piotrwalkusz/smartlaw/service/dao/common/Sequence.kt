package com.piotrwalkusz.smartlaw.service.dao.common

import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.ReturnDocument
import com.mongodb.client.model.Updates
import org.springframework.stereotype.Service

@Service
class Sequence(private val mongoDatabase: MongoDatabase) {

    companion object {
        private const val SEQUENCE_COLLECTION_NAME = "sequence"
        private const val KEY = "key"
        private const val SEQ = "seq"
    }

    fun getNext(model: Class<*>): String {
        return getNext(model.name)
    }

    fun getNext(key: String): String {
        val sequence = mongoDatabase.getCollection(SEQUENCE_COLLECTION_NAME).findOneAndUpdate(
                Filters.eq(KEY, key),
                Updates.inc(SEQ, 1),
                FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER))
        return sequence!!.getInteger(SEQ).toString()
    }
}