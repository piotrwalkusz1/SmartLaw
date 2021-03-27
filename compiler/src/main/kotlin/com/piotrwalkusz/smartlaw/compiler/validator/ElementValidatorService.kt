package com.piotrwalkusz.smartlaw.compiler.validator

import com.github.michaelbull.result.*
import com.piotrwalkusz.smartlaw.compiler.element.BasicType
import com.piotrwalkusz.smartlaw.compiler.validator.model.*
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.element.definition.Definition
import com.piotrwalkusz.smartlaw.core.model.element.enumdefinition.EnumDefinition
import com.piotrwalkusz.smartlaw.core.model.element.function.Function
import com.piotrwalkusz.smartlaw.core.model.element.state.State
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

class ElementValidatorService {

    fun validateElements(elements: List<Element>): List<ValidatedElement> {
        return elements
                .map { element -> validateElement(element, elements.filter { element !== it }) }
                .mapNotNull { it.get() }
    }

    fun validateElement(element: Element, otherElements: List<Element>): Result<ValidatedElement, List<ElementValidationError>> {
        return when (element) {
            is State -> {
                validateState(element, otherElements)
            }
            is EnumDefinition -> {
                validateEnumDefinition(element)
            }
            is Function -> {
                validateFunction(element)
            }
            else -> {
                Err(listOf(ElementValidationError("Validation of element ${element::class} is not supported")))
            }
        }
    }

    private fun validateState(state: State, otherElements: List<Element>): Result<ValidatedState, List<ElementValidationError>> {
        val allElements = listOf(state) + otherElements

        return binding {
            val type = validateType(state.type, allElements).bind()
            val defaultValue = state.defaultValue?.let { validateValue(type, it) }?.bind()

            ValidatedState(
                    name = state.name,
                    type = type,
                    defaultValue = defaultValue
            )
        }
    }

    private fun validateEnumDefinition(enumDefinition: EnumDefinition): Result<ValidatedEnumDefinition, List<ElementValidationError>> {
        return Ok(ValidatedEnumDefinition(
                name = enumDefinition.name,
                variants = enumDefinition.variants.map { it.name }
        ))
    }

    private fun validateFunction(function: Function): Result<ValidatedFunction, List<ElementValidationError>> {
        return Ok(ValidatedFunction(
                name = function.name
        ))
    }

    private fun validateValue(type: ValidatedType, metaValue: MetaValue): Result<ValidatedMetaValue, List<ElementValidationError>> {
        return when (type) {
            is ValidatedBasicType -> {
                if (metaValue is MetaPrimitiveValue) {
                    Ok(ValidatedBasicTypeMetaValue(metaValue, type))
                } else {
                    Err(listOf(ElementValidationError("Expected MetaPrimitiveValue as value of basic type")))
                }
            }
            is ValidatedEnumDefinitionRef -> {
                if (metaValue is MetaPrimitiveValue) {
                    Ok(ValidatedEnumMetaValue(metaValue, type))
                } else {
                    Err(listOf(ElementValidationError("Expected MetaPrimitiveValue as value of enum type")))
                }
            }
            else -> {
                Err(listOf(ElementValidationError("Only basic types can have literal values")))
            }
        }
    }

    private fun validateType(type: Type, elements: List<Element>): Result<ValidatedType, List<ElementValidationError>> {
        if (type !is DefinitionRef) {
            return Err(listOf(ElementValidationError("Expected instance of DefinitionRef class. ${type.javaClass} class was.")))
        }

        val basicType = BasicType.values().find { it.id == type.definition }
        if (basicType != null) {
            return Ok(ValidatedBasicType(basicType))
        }

        val element = elements
                .find { it.id == type.definition }
                ?: return Err(listOf(ElementValidationError("Element with id ${type.definition} not found")))

        if (element is Definition) {
            return Ok(ValidatedDefinitionRef(element))
        }
        if (element is EnumDefinition) {
            return Ok(ValidatedEnumDefinitionRef(element))
        }

        return Err(listOf(ElementValidationError("Element with id ${type.definition} expected to be Definition or EnumDefinition")))
    }

    private fun isBasisType(type: Type): Boolean {
        if (type !is DefinitionRef) {
            return false
        }

        return BasicType.values().find { it.id == type.definition } != null
    }
}