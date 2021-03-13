package com.piotrwalkusz.smartlaw.core.model.element.action

import com.piotrwalkusz.smartlaw.annotationprocessor.GenerateTemplate
import com.piotrwalkusz.smartlaw.core.model.common.Id

@GenerateTemplate
data class ActionRef(val actionId: Id)