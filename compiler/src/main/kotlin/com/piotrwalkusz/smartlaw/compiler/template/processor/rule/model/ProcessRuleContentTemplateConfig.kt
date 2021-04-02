package com.piotrwalkusz.smartlaw.compiler.template.processor.rule.model

import com.piotrwalkusz.smartlaw.compiler.provider.RuleProvider
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element

data class ProcessRuleContentTemplateConfig(
        val linksByElementsIds: Map<Id, String>,
        val elements: List<Element>,
        val ruleProvider: RuleProvider,
        val addStyleToContent: Boolean
) : ProcessRuleTemplateConfig