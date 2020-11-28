package com.piotrwalkusz.smartlaw.service.service;

import com.piotrwalkusz.smartlaw.service.dao.ModuleDao
import com.piotrwalkusz.smartlaw.service.dao.ProjectDao
import com.piotrwalkusz.smartlaw.service.dao.common.Sequence
import com.piotrwalkusz.smartlaw.service.model.Project
import org.springframework.stereotype.Service

@Service
class ProjectService(
        private val projectDao: ProjectDao,
        private val moduleDao: ModuleDao,
        private val sequence: Sequence
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
}
