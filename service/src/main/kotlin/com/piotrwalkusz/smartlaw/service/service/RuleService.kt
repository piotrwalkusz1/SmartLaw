package com.piotrwalkusz.smartlaw.service.service

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.piotrwalkusz.smartlaw.compiler.meta.IntegerMetaType
import com.piotrwalkusz.smartlaw.compiler.meta.LocalDateMetaType
import com.piotrwalkusz.smartlaw.compiler.meta.StringMetaType
import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInterface
import com.piotrwalkusz.smartlaw.service.controller.dto.GetRulesArgumentsTypesDto
import com.piotrwalkusz.smartlaw.service.controller.dto.SearchRuleDto
import com.piotrwalkusz.smartlaw.service.controller.dto.SearchRuleInterfaceDto
import com.piotrwalkusz.smartlaw.service.dao.DocumentDao
import org.litote.kmongo.ascending
import org.litote.kmongo.contains
import org.litote.kmongo.eq
import org.litote.kmongo.regex
import org.springframework.stereotype.Service
import java.util.logging.Filter

@Service
class RuleService(private val documentDao: DocumentDao, private val projectService: ProjectService) {

    fun getBasicRulesArgumentsTypes(): List<Id> {
        return listOf(
                IntegerMetaType().getId(),
                StringMetaType().getId(),
                LocalDateMetaType().getId()
        )
    }

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

    fun searchRules(searchRuleDto: SearchRuleDto): List<Rule> {
        val documentsIds = projectService.getDocumentsIdsInProjectAndModules(searchRuleDto.projectId)
        val filters = listOfNotNull(
                searchRuleDto.ruleId?.let { Rule::id eq it },
                searchRuleDto.searchPhrase?.let { Rule::name regex it },
                searchRuleDto.ruleInterfaceId?.let { Rule::interfaces contains it })
        val filter = if (filters.isEmpty()) null else Filters.and(filters)

        return documentDao.getRulesByFilter(filter, documentsIds, 10, ascending(Rule::name))
    }

    fun searchRulesInterfaces(searchRuleInterfaceDto: SearchRuleInterfaceDto): List<RuleInterface> {
        val documentsIds = projectService.getDocumentsIdsInProjectAndModules(searchRuleInterfaceDto.projectId)
        val filters = listOfNotNull(
                searchRuleInterfaceDto.ruleInterfaceId?.let { RuleInterface::id eq it },
                searchRuleInterfaceDto.searchPhrase?.let { RuleInterface::name regex it })
        val filter = if (filters.isEmpty()) null else Filters.and(filters)

        return documentDao.getRulesInterfacesByFilter(filter, documentsIds, 10, ascending(RuleInterface::name))
    }

    fun getRulesArgumentsTypes(request: GetRulesArgumentsTypesDto): List<Id> {
        val documentsIds = projectService.getDocumentsIdsInProjectAndModules(request.projectId)
        val rulesInterfacesIds = documentDao.getRulesInterfacesByFilter(null, documentsIds).map { it.id }

        return getBasicRulesArgumentsTypes() + rulesInterfacesIds
    }
}