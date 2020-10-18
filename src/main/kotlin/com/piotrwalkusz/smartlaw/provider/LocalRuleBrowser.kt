package com.piotrwalkusz.smartlaw.provider

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.document.Document
import com.piotrwalkusz.smartlaw.model.document.RulesContainer

class LocalRuleBrowser(documents: List<Document>) : RuleBrowser {

    private val documentIdByRuleId: Map<Id, Id>

    init {
        documentIdByRuleId = documents
                .filterIsInstance(RulesContainer::class.java)
                .flatMap { document -> document.rules.map { rule -> rule.id to document.id } }
                .toMap()
    }

    override fun getIdOfDocumentContainingRule(ruleId: Id): Id? {
        return documentIdByRuleId.get(ruleId)
    }
}