package com.piotrwalkusz.smartlaw.compiler.validator

import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

data class RuleArgumentValidationResult(
        val argument: MetaArgument,
        val value: MetaValue,
        val results: List<ValidationResult>
)