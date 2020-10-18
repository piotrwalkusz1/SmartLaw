package com.piotrwalkusz.smartlaw.provider

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.document.RulesContainer
import com.piotrwalkusz.smartlaw.model.rule.Rule

class RuleProvider(
        private val ruleBrowser: RuleBrowser,
        private val documentProvider: DocumentProvider
) {

    fun getRule(ruleId: Id): Rule? {
        return ruleBrowser.getIdOfDocumentContainingRule(ruleId)
                ?.let{ documentId -> documentProvider.getDocument(documentId) }
                ?.let { document -> document as RulesContainer }
                ?.rules
                ?.find { it.id == ruleId }
    }
}