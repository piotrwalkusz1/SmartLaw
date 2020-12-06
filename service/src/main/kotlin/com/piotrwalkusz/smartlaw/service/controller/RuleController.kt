package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.service.controller.dto.SearchRuleDto
import com.piotrwalkusz.smartlaw.service.service.RuleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rules")
class RuleController(
        private val ruleService: RuleService
) {

    @PostMapping("/search")
    fun getRule(@RequestBody searchRuleDto: SearchRuleDto): List<Rule> {
        return ruleService.searchRule(searchRuleDto)
    }
}