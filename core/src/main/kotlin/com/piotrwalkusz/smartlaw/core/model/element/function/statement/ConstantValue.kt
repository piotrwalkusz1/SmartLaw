package com.piotrwalkusz.smartlaw.core.model.element.function.statement

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

@GenerateTemplate
data class ConstantValue(
        val value: MetaValue,
        val type: Type
) : Expression