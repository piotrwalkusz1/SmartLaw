package com.piotrwalkusz.smartlaw.service.patch;

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.piotrwalkusz.smartlaw.service.example.CarSalesContractExample
import com.piotrwalkusz.smartlaw.service.example.CivilCodeExample
import com.piotrwalkusz.smartlaw.service.service.DocumentService
import com.piotrwalkusz.smartlaw.service.service.ProjectService
import io.changock.migration.api.annotations.NonLockGuarded


@ChangeLog
class P001_Initialize {

    @ChangeSet(order = "001", id = "P001_Initialize_001", author = "piotrwalkusz")
    fun P001_Initialize_001(@NonLockGuarded projectService: ProjectService, @NonLockGuarded documentService: DocumentService) {
        val libraryId = documentService.createDocument(CarSalesContractExample.library).id
        val contractId = documentService.createDocument(CarSalesContractExample.contract).id
        projectService.createProject("Test", listOf(contractId, libraryId))
    }

    @ChangeSet(order = "002", id = "P001_Initialize_002", author = "piotrwalkusz")
    fun P001_Initialize_002(@NonLockGuarded projectService: ProjectService, @NonLockGuarded documentService: DocumentService) {
        val legalActId = documentService.createDocument(CivilCodeExample.legalAct).id
        projectService.createModule("Prawo polskie", listOf(legalActId))
    }
}
