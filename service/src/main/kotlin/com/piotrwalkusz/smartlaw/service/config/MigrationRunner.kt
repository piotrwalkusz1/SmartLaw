package com.piotrwalkusz.smartlaw.service.config

import com.github.cloudyrock.mongock.driver.mongodb.sync.v4.driver.MongoSync4Driver
import com.github.cloudyrock.standalone.MongockStandalone
import com.mongodb.client.MongoClient
import com.piotrwalkusz.smartlaw.service.service.DocumentService
import com.piotrwalkusz.smartlaw.service.service.ProjectService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class MigrationRunner(
        private val mongoClient: MongoClient,
        private val projectService: ProjectService,
        private val documentService: DocumentService
) {

    @EventListener(ApplicationReadyEvent::class)
    fun runMigration() {
        MongockStandalone.builder()
                .setDriver(MongoSync4Driver.withDefaultLock(mongoClient, MongoConfig.DATABASE))
                .addChangeLogsScanPackage("com.piotrwalkusz.smartlaw.service.patch")
                .addDependency(projectService)
                .addDependency(documentService)
                .buildRunner()
                .execute()
    }
}