package com.piotrwalkusz.smartlaw.compiler.provider

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.document.Document
import com.piotrwalkusz.smartlaw.core.model.document.RulesContainer
import com.piotrwalkusz.smartlaw.core.model.rule.Rule

class InMemoryRuleProvider(documentsList: List<Document>) : RuleProvider {

    private val documents: Map<Id, Document> = documentsList.associateBy { it.id }
    private val documentIdByRuleId: Map<Id, Id>

    init {
        documentIdByRuleId = documentsList
                .filterIsInstance(RulesContainer::class.java)
                .flatMap { document -> document.rules.map { rule -> rule.id to document.id } }
                .toMap()
    }

    override fun getRule(ruleId: Id): Rule? {
        return documentIdByRuleId[ruleId]
                ?.let { documentId -> documents[documentId] }
                ?.let { document -> document as RulesContainer }
                ?.rules
                ?.find { it.id == ruleId }
    }
}