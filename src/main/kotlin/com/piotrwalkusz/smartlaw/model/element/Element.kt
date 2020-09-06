package com.piotrwalkusz.smartlaw.model.element

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.element.annotation.Annotation

interface Element {
    val id: Id
    val annotations: List<Annotation>
}