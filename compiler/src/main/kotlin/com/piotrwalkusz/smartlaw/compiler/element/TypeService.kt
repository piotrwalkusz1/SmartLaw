package com.piotrwalkusz.smartlaw.compiler.element

class TypeService {

    companion object {
        val BASIC_TYPES = listOf(
                UIntType(),
                AddressType()
        )
    }
}