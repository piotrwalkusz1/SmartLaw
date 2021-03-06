package com.piotrwalkusz.smartlaw.compiler.meta

import com.piotrwalkusz.smartlaw.compiler.common.output.Output
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue
import java.time.LocalDate
import java.time.format.DateTimeParseException

class LocalDateMetaType : MetaType() {

    override fun getId(): Id {
        return Id("LocalDate")
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

        try {
            LocalDate.parse(metaValue.value)
        } catch (exception: DateTimeParseException) {
            return "Value cannot be parsed as local date"
        }

        return null
    }
}