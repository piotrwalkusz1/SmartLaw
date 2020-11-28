package com.piotrwalkusz.smartlaw.service.dao;

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.piotrwalkusz.smartlaw.service.model.Project
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.springframework.stereotype.Service

@Service
class ProjectDao(private val mongoDatabase: MongoDatabase) {

    fun getProjectById(projectId: String): Project? {
        return getCollection().findOne(Project::id eq projectId)
    }

    fun insertProject(project: Project) {
        getCollection().insertOne(project)
    }

    private fun getCollection(): MongoCollection<Project> {
        return mongoDatabase.getCollection("projects", Project::class.java)
    }
}
