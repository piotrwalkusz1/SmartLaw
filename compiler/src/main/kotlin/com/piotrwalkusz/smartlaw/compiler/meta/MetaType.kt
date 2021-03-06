package com.piotrwalkusz.smartlaw.compiler.meta

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

abstract class MetaType {

    abstract fun getId(): Id

    abstract fun convertMetaValueToTemplateParameter(metaValue: MetaValue): Any?

    abstract fun validateMetaValue(metaValue: MetaValue): String?
}