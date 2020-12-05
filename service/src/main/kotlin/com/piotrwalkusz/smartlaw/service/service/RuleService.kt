package com.piotrwalkusz.smartlaw.service.service

import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.service.dao.DocumentDao
import org.springframework.stereotype.Service

@Service
class RuleService(private val documentDao: DocumentDao, private val projectService: ProjectService) {

    fun getRuleByIdFromProject(ruleId: Id, projectId: String): Rule? {
        return getRuleProviderForProject(projectId).getRule(ruleId)
    }

    fun getRuleByIdFromDocuments(ruleId: Id, documentsIds: List<String>): Rule? {
        return documentDao.getRuleById(ruleId, documentsIds)
    }

    fun getRuleProviderForProject(projectId: String): RuleProvider {
        val documentsIds = projectService.getDocumentsIdsInProjectAndModules(projectId)

        return object : RuleProvider {
            override fun getRule(ruleId: Id): Rule? {
                return getRuleByIdFromDocuments(ruleId, documentsIds)
            }
        }
    }
}