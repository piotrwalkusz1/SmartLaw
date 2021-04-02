package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue

data class ValidatedConstantValue(
        val value: MetaPrimitiveValue,
        val type: ValidatedType
) : ValidatedExpression