package com.piotrwalkusz.smartlaw.provider

import com.piotrwalkusz.smartlaw.model.common.Id

interface RuleBrowser {

    fun getIdOfDocumentContainingRule(ruleId: Id): Id?
}