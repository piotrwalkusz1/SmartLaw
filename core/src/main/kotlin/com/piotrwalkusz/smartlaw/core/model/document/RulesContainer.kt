package com.piotrwalkusz.smartlaw.core.model.document

import com.piotrwalkusz.smartlaw.core.model.rule.Rule

interface RulesContainer : Document {

    val rules: List<Rule>
}