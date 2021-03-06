package com.piotrwalkusz.smartlaw.compiler.meta

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

class StringMetaType : MetaType() {

    override fun getId(): Id {
        return Id("String")
    }

    override fun convertMetaValueToTemplateParameter(metaValue: MetaValue): Any? {
        if (metaValue !is MetaPrimitiveValue) {
            Output.get().addError("Required PrimitiveValue")
            return null
        }

        return metaValue.value
    }

    override fun validateMetaValue(metaValue: MetaValue): String? {
        if (metaValue !is MetaPrimitiveValue) {
            return "Required PrimitiveValue"
        }

        return null
    }
}