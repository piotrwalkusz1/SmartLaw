package com.piotrwalkusz.smartlaw.service.controller.dto

import com.piotrwalkusz.smartlaw.core.model.common.Id


data class SearchRuleInterfaceDto(
        val searchPhrase: String?,
        val ruleInterfaceId: Id?,
        val projectId: String
)