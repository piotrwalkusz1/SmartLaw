package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue

data class ValidatedEnumMetaValue(
        val metaValue: MetaPrimitiveValue,
        val enumDefinitionRef: ValidatedEnumDefinitionRef
) : ValidatedMetaValue