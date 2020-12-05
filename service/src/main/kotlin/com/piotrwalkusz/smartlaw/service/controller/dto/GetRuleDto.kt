package com.piotrwalkusz.smartlaw.service.controller.dto

import com.piotrwalkusz.smartlaw.core.model.common.Id


data class GetRuleDto(
        val ruleId: Id,
        val projectId: String
)