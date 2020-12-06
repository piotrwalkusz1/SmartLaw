package com.piotrwalkusz.smartlaw.service.service

import com.mongodb.client.model.Filters
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.service.controller.dto.SearchRuleDto
import com.piotrwalkusz.smartlaw.service.dao.DocumentDao
import org.litote.kmongo.eq
import org.litote.kmongo.regex
import org.springframework.stereotype.Service
import java.util.logging.Filter

@Service
class RuleService(private val documentDao: DocumentDao, private val projectService: ProjectService) {

    fun getRuleByIdFromProject(ruleId: Id, projectId: String): Rule? {
        return getRuleProviderForProject(projectId).getRule(ruleId)
    }

    fun getRuleProviderForProject(projectId: String): RuleProvider {
        val documentsIds = projectService.getDocumentsIdsInProjectAndModules(projectId)

        return object : RuleProvider {
            override fun getRule(ruleId: Id): Rule? {
                return getRuleByIdFromDocuments(ruleId, documentsIds)
            }
        }
    }

    fun getRuleByIdFromDocuments(ruleId: Id, documentsIds: List<String>): Rule? {
        return documentDao.getRuleById(ruleId, documentsIds)
    }

    fun searchRule(searchRuleDto: SearchRuleDto): List<Rule> {
        val documentsIds = projectService.getDocumentsIdsInProjectAndModules(searchRuleDto.projectId)
        val filters = listOfNotNull(
                searchRuleDto.ruleId?.let { Rule::id eq it },
                searchRuleDto.searchPhrase?.let { Rule::name regex it })
        val filter = if (filters.isEmpty()) null else Filters.and(filters)

        return documentDao.getRulesByFilter(filter, documentsIds)
    }
}