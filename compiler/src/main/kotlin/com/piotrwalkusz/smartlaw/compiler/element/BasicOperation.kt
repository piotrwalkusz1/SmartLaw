package com.piotrwalkusz.smartlaw.compiler.element

import com.piotrwalkusz.smartlaw.core.model.common.Id

enum class BasicOperation(val id: Id) {

    TRANSFER(Id("TRANSFER")),
    BALANCE(Id("BALANCE")),
    EQUALS(Id("EQUALS")),
    MULTIPLY(Id("MULTIPLY")),
    SENDER(Id("SENDER")),
    TRANSFER_VALUE(Id("TRANSFER_VALUE"))
}