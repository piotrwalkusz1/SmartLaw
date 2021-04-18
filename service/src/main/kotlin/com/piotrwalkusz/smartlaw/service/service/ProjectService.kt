package com.piotrwalkusz.smartlaw.service.service;

import com.piotrwalkusz.smartlaw.core.model.document.LegalAct
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.service.controller.dto.SearchProjectsDto
import com.piotrwalkusz.smartlaw.service.dao.DocumentDao
import com.piotrwalkusz.smartlaw.service.dao.ModuleDao
import com.piotrwalkusz.smartlaw.service.dao.ProjectDao
import com.piotrwalkusz.smartlaw.service.dao.common.Sequence
import com.piotrwalkusz.smartlaw.service.model.DocumentModule
import com.piotrwalkusz.smartlaw.service.model.Project
import org.litote.kmongo.ascending
import org.litote.kmongo.regex
import org.springframework.stereotype.Service

@Service
class ProjectService(
        private val projectDao: ProjectDao,
        private val moduleDao: ModuleDao,
        private val sequence: Sequence,
        private val documentDao: DocumentDao
) {

    fun getDocumentsIdsInProjectAndModules(projectId: String): List<String> {
        val project = getProject(projectId)

        return listOf(
                project.documentsIds,
                moduleDao.getDocumentsIdsByModulesIds(project.modulesIds)
        ).flatten()
    }

    fun getProject(projectId: String): Project {
        return projectDao.getProjectById(projectId)
                ?: throw IllegalArgumentException("No project with id $projectId")
    }

    fun createProject(name: String, documentsIds: List<String> = listOf()): Project {
        val project = Project(
                id = sequence.getNext(Project::class.java),
                name = name,
                documentsIds = documentsIds)
        projectDao.insertProject(project)

        return project
    }

    fun searchProjects(searchProjectsDto: SearchProjectsDto): List<Project> {
        val filter = Project::name regex searchProjectsDto.searchPhrase

        return projectDao.getProjectsByFilter(filter, 10, ascending(Project::name))
    }

    fun createModule(name: String, documentsIds: List<String>): DocumentModule {
        val module = DocumentModule(
                id = sequence.getNext(Project::class.java),
                name = name,
                documentsIds = documentsIds)
        moduleDao.insertModule(module)

        return module
    }

    fun getExternalElementsInProject(projectId: String): List<Element> {
        val documentsIds = getDocumentsIdsInProjectAndModules(projectId)

        return documentDao.getLegalActs(documentsIds)
                .flatMap { legalAct -> (legalAct.document as LegalAct).elements }
    }
}
