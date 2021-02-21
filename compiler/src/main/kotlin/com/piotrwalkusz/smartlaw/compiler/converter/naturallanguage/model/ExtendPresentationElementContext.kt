package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model

import com.piotrwalkusz.smartlaw.core.model.common.Id

data class ExtendPresentationElementContext(
        val linksByElementsIds: Map<Id, String>
)