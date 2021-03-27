package com.piotrwalkusz.smartlaw.compiler.validator.model

import com.piotrwalkusz.smartlaw.compiler.element.BasicType

data class ValidatedBasicType(
        val basicType: BasicType
) : ValidatedType