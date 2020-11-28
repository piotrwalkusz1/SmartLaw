package com.piotrwalkusz.smartlaw.service.service

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.service.dao.DocumentDao
import org.springframework.stereotype.Service

@Service
class RuleService(private val documentDao: DocumentDao) {

    fun getRuleById(ruleId: Id, documentsIds: List<String>): Rule? {
        return documentDao.getRuleById(ruleId, documentsIds)
    }
}