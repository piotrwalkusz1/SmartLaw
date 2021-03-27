package com.piotrwalkusz.smartlaw.compiler.validator

import com.github.michaelbull.result.*
import com.piotrwalkusz.smartlaw.compiler.element.BasicType
import com.piotrwalkusz.smartlaw.compiler.validator.model.*
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.element.definition.Definition
import com.piotrwalkusz.smartlaw.core.model.element.enumdefinition.EnumDefinition
import com.piotrwalkusz.smartlaw.core.model.element.function.Function
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.StateVariableRef
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.VariableRef
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Assignment
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.EnumValue
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Expression
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.Statement
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
                validateFunction(element, otherElements)
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

    private fun validateFunction(function: Function, otherElements: List<Element>): Result<ValidatedFunction, List<ElementValidationError>> = binding {
        val allElements = listOf(function) + otherElements
        val body = function.body.map { validateStatement(it, allElements).bind() }

        ValidatedFunction(
                name = function.name,
                body = body
        )
    }

    private fun validateExpression(expression: Expression, elements: List<Element>): Result<ValidatedExpression, List<ElementValidationError>> = binding {
        val validatedStatement = validateStatement(expression, elements).bind()
        if (validatedStatement is ValidatedExpression) {
            validatedStatement
        } else {
            error("This statement is not expression").bind()
        }
    }

    private fun validateStatement(statement: Statement, elements: List<Element>): Result<ValidatedStatement, List<ElementValidationError>> = binding {
        when (statement) {
            is Assignment -> {
                val variable = validateVariableRef(statement.variable, elements).bind()
                val value = validateExpression(statement.value, elements).bind()
                ValidatedAssignment(
                        variable = variable,
                        value = value
                )
            }
            is EnumValue -> {
                val enumDefinition = findElement<EnumDefinition>(statement.enumDefinition, elements).bind()

                ValidatedEnumValue(
                        value = statement.value,
                        enumDefinition = enumDefinition

                )
            }
            else -> {
                error("${statement.javaClass} is not supported").bind()
            }
        }
    }

    private fun validateVariableRef(variableRef: VariableRef, elements: List<Element>): Result<ValidatedVariableRef, List<ElementValidationError>> = binding {
        when (variableRef) {
            is StateVariableRef -> {
                val state = findElement<State>(variableRef.state, elements).bind()

                ValidatedSimpleVariableRef(
                        name = state.name,
                        type = validateType(state.type, elements).bind()
                )
            }
            else -> {
                error("${variableRef.javaClass} is not supported").bind()
            }
        }
    }

    private fun validateValue(type: ValidatedType, metaValue: MetaValue): Result<ValidatedValue, List<ElementValidationError>> {
        return when (type) {
            is ValidatedBasicType -> {
                if (metaValue is MetaPrimitiveValue) {
                    Ok(ValidatedBasicTypeLiteralValue(metaValue, type))
                } else {
                    Err(listOf(ElementValidationError("Expected MetaPrimitiveValue as value of basic type")))
                }
            }
            is ValidatedEnumDefinitionRef -> {
                if (metaValue is MetaPrimitiveValue) {
                    Ok(ValidatedEnumValue(metaValue.value, type.enumDefinition))
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

        return error("Element with id ${type.definition} expected to be Definition or EnumDefinition")
    }

    private fun isBasisType(type: Type): Boolean {
        if (type !is DefinitionRef) {
            return false
        }

        return BasicType.values().find { it.id == type.definition } != null
    }

    private inline fun <reified T : Element> findElement(id: Id, elements: List<Element>): Result<T, List<ElementValidationError>> {
        val element = elements
                .filterIsInstance<T>()
                .find { it.id == id }
                ?: return Err(listOf(ElementValidationError("Element with id $id and type ${T::class.java} not found")))

        return Ok(element)
    }

    private fun error(message: String): Result<Nothing, List<ElementValidationError>> {
        return Err(listOf(ElementValidationError(message)))
    }
}