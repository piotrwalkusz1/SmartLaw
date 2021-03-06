package com.piotrwalkusz.smartlaw.compiler.meta

class MetaTypeService {

    companion object {
        val DEFAULT_META_TYPES = listOf(
                IntegerMetaType(),
                StringMetaType(),
                LocalDateMetaType()
        )
    }
}