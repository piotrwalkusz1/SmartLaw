package com.piotrwalkusz.smartlaw.service.controller.dto

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue


data class ProcessTemplateDto(
        val projectId: String,
        val documentId: String,
        val ruleId: Id,
        val arguments: List<MetaValue>
)