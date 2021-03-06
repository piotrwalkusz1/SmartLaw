package com.piotrwalkusz.smartlaw.compiler.element

import com.piotrwalkusz.smartlaw.core.model.common.Id

class AddressType : BasicType() {

    override fun getId(): Id {
        return Id("ADDRESS")
    }
}