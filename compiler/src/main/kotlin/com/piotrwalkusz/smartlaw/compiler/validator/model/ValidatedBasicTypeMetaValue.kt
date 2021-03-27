package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue

data class ValidatedBasicTypeMetaValue(
        val metaValue: MetaPrimitiveValue,
        val basicValue: ValidatedBasicType
) : ValidatedMetaValue