package com.piotrwalkusz.smartlaw.compiler.template.processor.rule

import com.piotrwalkusz.smartlaw.compiler.rule.model.RuleArgumentWithValue
import com.piotrwalkusz.smartlaw.compiler.template.processor.TemplateProcessorService
import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.model.ProcessRuleElementsTemplateConfig
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.template.Template

class RuleElementsTemplateProcessor(
        templateProcessorService: TemplateProcessorService
) : RuleTemplateProcessor<List<Element>, ProcessRuleElementsTemplateConfig>(templateProcessorService) {

    fun processTemplate(template: Template<List<Element>>, ruleArguments: List<RuleArgumentWithValue>): List<Element> {
        return processTemplate(template, ruleArguments, ProcessRuleElementsTemplateConfig())
    }
}