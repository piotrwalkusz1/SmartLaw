package com.piotrwalkusz.smartlaw.compiler.provider

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.rule.Rule

interface RuleProvider {

    fun getRule(ruleId: Id): Rule?
}