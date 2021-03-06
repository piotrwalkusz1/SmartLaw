package com.piotrwalkusz.smartlaw.compiler.meta

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.compiler.validator.ValidationResult
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

class IntegerMetaType : MetaType() {

    override fun getId(): Id {
        return Id("Integer")
    }

    override fun convertMetaValueToTemplateParameter(metaValue: MetaValue): Any? {
        if (metaValue !is MetaPrimitiveValue) {
            Output.get().addError("Required PrimitiveValue")
            return null
        }

        val number = metaValue.value.toIntOrNull()
        if (number == null) {
            Output.get().addError("Cannot convert \"${metaValue.value}\" to number")
        }

        return number
    }

    override fun validateMetaValue(metaValue: MetaValue): String? {
        if (metaValue !is MetaPrimitiveValue) {
            return "Required PrimitiveValue"
        }

        if (metaValue.value.toIntOrNull() == null) {
            return "Value cannot be parsed as number"
        }

        return null
    }
}