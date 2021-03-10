package com.piotrwalkusz.smartlaw.compiler.template.processor.rule.model

import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.core.model.common.Id

data class ProcessRuleContentTemplateConfig(
        val linksByElementsIds: Map<Id, String>,
        val ruleProvider: RuleProvider,
        val addStyleToContent: Boolean
) : ProcessRuleTemplateConfig