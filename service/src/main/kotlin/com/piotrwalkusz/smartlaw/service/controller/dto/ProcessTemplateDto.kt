package com.piotrwalkusz.smartlaw.service.controller.dto

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

data class ProcessTemplateDto(
        val ruleId: Id,
        val arguments: List<MetaValue>
)