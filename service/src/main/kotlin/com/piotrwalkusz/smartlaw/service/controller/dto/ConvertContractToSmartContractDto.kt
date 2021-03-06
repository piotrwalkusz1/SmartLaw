package com.piotrwalkusz.smartlaw.service.controller.dto

import com.piotrwalkusz.smartlaw.core.model.document.Contract

data class ConvertContractToSmartContractDto(
        val contract: Contract
)