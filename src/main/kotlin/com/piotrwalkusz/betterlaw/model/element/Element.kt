package com.piotrwalkusz.betterlaw.model.element

import com.piotrwalkusz.betterlaw.model.common.Id
import com.piotrwalkusz.betterlaw.model.element.annotation.Annotation

interface Element {
    val id: Id
    val annotations: List<Annotation>
}