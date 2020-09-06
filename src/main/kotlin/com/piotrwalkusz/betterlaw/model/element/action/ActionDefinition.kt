package com.piotrwalkusz.betterlaw.model.element.action

import com.piotrwalkusz.betterlaw.model.common.Id
import com.piotrwalkusz.betterlaw.model.element.Element
import com.piotrwalkusz.betterlaw.model.element.annotation.Annotation

data class ActionDefinition(
        override val id: Id,
        override val annotations: List<Annotation> = listOf(),
        val name: String,
        val description: String? = null,
        val arguments: List<ActionArgument> = listOf(),
        val invocationValidators: List<ActionInvocationValidator> = listOf()
) : Element