package com.piotrwalkusz.smartlaw.compiler.converter.extend

import com.piotrwalkusz.smartlaw.compiler.template.processor.rule.model.ProcessRuleContentTemplateConfig
import com.piotrwalkusz.smartlaw.core.model.element.Element

data class ExtendPresentationElementContext(
        val processRuleContentTemplateConfig: ProcessRuleContentTemplateConfig,
        val elements: MutableList<Element> = mutableListOf(),
        val elementsToValidate: MutableList<Pair<Element, ExtendedRuleInvocationPresentationElement>> = mutableListOf()
)