package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.core.model.document.Document
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.service.controller.dto.GetRuleDto
import com.piotrwalkusz.smartlaw.service.service.DocumentService
import com.piotrwalkusz.smartlaw.service.service.RuleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rules")
class RuleController(
        private val ruleService: RuleService
) {

    @PostMapping("/search")
    fun getRule(@RequestBody getRuleDto: GetRuleDto): Rule? {
        return ruleService.getRuleByIdFromProject(getRuleDto.ruleId, getRuleDto.projectId)
    }
}