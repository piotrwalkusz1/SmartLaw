package com.piotrwalkusz.smartlaw.model.document

import com.piotrwalkusz.smartlaw.model.rule.Rule

interface RulesContainer : Document {

    val rules: List<Rule>
}