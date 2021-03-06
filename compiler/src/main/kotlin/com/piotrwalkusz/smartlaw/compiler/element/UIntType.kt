package com.piotrwalkusz.smartlaw.compiler.element

import com.piotrwalkusz.smartlaw.core.model.common.Id

class UIntType : BasicType() {

    override fun getId(): Id {
        return Id("UINT")
    }
}