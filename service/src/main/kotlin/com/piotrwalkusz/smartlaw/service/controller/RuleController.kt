package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInterface
import com.piotrwalkusz.smartlaw.service.controller.dto.GetRulesArgumentsTypesDto
import com.piotrwalkusz.smartlaw.service.controller.dto.SearchRuleDto
import com.piotrwalkusz.smartlaw.service.controller.dto.SearchRuleInterfaceDto
import com.piotrwalkusz.smartlaw.service.service.RuleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rules")
class RuleController(
        private val ruleService: RuleService
) {

    @PostMapping("/search")
    fun getRules(@RequestBody searchRuleDto: SearchRuleDto): List<Rule> {
        return ruleService.searchRules(searchRuleDto)
    }

    @PostMapping("/interfaces/search")
    fun getRulesInterfaces(@RequestBody searchRuleInterfacesDto: SearchRuleInterfaceDto): List<RuleInterface> {
        return ruleService.searchRulesInterfaces(searchRuleInterfacesDto)
    }

    @PostMapping("/arguments/types")
    fun getRulesArgumentsTypes(@RequestBody request: GetRulesArgumentsTypesDto): List<Id> {
        return ruleService.getRulesArgumentsTypes(request)
    }
}