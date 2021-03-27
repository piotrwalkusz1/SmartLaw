package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue

data class ValidatedBasicTypeLiteralValue(
        val metaValue: MetaPrimitiveValue,
        val basicValue: ValidatedBasicType
) : ValidatedValue