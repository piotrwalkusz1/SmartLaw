package com.piotrwalkusz.smartlaw.compiler.element

import com.piotrwalkusz.smartlaw.core.model.common.Id

enum class BasicTypes(val id: Id) {

    UINT(Id("UINT")),
    ADDRESS(Id("ADDRESS")),
    STRING(Id("STRING"))
}