package com.piotrwalkusz.smartlaw.compiler.validator

import com.github.michaelbull.result.*
import com.piotrwalkusz.smartlaw.compiler.element.BasicOperation
import com.piotrwalkusz.smartlaw.compiler.element.BasicType
import com.piotrwalkusz.smartlaw.compiler.validator.model.*
import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.element.Element
import com.piotrwalkusz.smartlaw.core.model.element.action.ActionArgument
import com.piotrwalkusz.smartlaw.core.model.element.action.ActionDefinition
import com.piotrwalkusz.smartlaw.core.model.element.actionvalidation.ActionValidation
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.element.common.type.Type
import com.piotrwalkusz.smartlaw.core.model.element.definition.Definition
import com.piotrwalkusz.smartlaw.core.model.element.enumdefinition.EnumDefinition
import com.piotrwalkusz.smartlaw.core.model.element.function.Function
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.FunctionArgument
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.StateVariableRef
import com.piotrwalkusz.smartlaw.core.model.element.function.argument.VariableRef
import com.piotrwalkusz.smartlaw.core.model.element.function.statement.*
import com.piotrwalkusz.smartlaw.core.model.element.state.State
import com.piotrwalkusz.smartlaw.core.model.meta.MetaMapValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.meta.MetaValue

class ElementValidatorService {

    fun validateElements(elements: List<Element>, externalElements: List<Element>): List<ValidatedElement> {
        val allElements = elements + externalElements

        return elements
                .map { element -> validateElement(element, allElements) }
                .mapNotNull { it.get() }
    }

    fun validateElement(element: Element, allElements: List<Element>): Result<ValidatedElement, List<ElementValidationError>> {
        return when (element) {
            is State -> {
                validateState(element, allElements)
            }
            is EnumDefinition -> {
                validateEnumDefinition(element)
            }
            is Function -> {
                validateFunction(element, allElements)
            }
            is ActionDefinition -> {
                validateActionDefinition(element, allElements)
            }
            is ActionValidation -> {
                validateActionValidation(element, allElements)
            }
            else -> {
                Err(listOf(ElementValidationError("Validation of element ${element::class} is not supported")))
            }
        }
    }

    private fun validateActionDefinition(actionDefinition: ActionDefinition, allElements: List<Element>): Result<ValidatedActionDefinition, List<ElementValidationError>> {
        return binding {
            val arguments = actionDefinition.arguments.map { validateActionArgument(it, allElements).bind() }
            val function = findElement<Function>(actionDefinition.function.id, allElements).bind()

            ValidatedActionDefinition(
                    action = actionDefinition,
                    name = actionDefinition.name,
                    arguments = arguments,
                    function = function,
                    functionRef = actionDefinition.function
            )
        }
    }

    private fun validateActionValidation(actionValidation: ActionValidation, allElements: List<Element>): Result<ValidatedActionValidation, List<ElementValidationError>> {
        return binding {
            ValidatedActionValidation(
                    name = actionValidation.name,
                    action = findElement<ActionDefinition>(actionValidation.action.actionId, allElements).bind(),
                    condition = validateExpression(actionValidation.condition, allElements).bind()
            )
        }
    }

    private fun validateActionArgument(actionArgument: ActionArgument, elements: List<Element>): Result<ValidatedActionArgument, List<ElementValidationError>> = binding {
        ValidatedActionArgument(
                name = actionArgument.name,
                type = validateType(actionArgument.type, elements).bind()
        )
    }

    private fun validateState(state: State, allElements: List<Element>): Result<ValidatedState, List<ElementValidationError>> {
        return binding {
            val type = validateType(state.type, allElements).bind()
            val defaultValue = state.defaultValue?.let { validateValue(type, it, allElements) }?.bind()

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

    private fun validateFunction(function: Function, allElements: List<Element>): Result<ValidatedFunction, List<ElementValidationError>> = binding {
        val body = function.body.map { validateStatement(it, allElements).bind() }

        ValidatedFunction(
                name = function.name,
                body = body
        )
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
            is Expression -> {
                validateExpression(statement, elements).bind()
            }
            else -> {
                error("${statement.javaClass} is not supported").bind()
            }
        }
    }

    private fun validateExpression(expression: Expression, elements: List<Element>): Result<ValidatedExpression, List<ElementValidationError>> = binding {
        when (expression) {
            is EnumValue -> {
                val enumDefinition = findElement<EnumDefinition>(expression.enumDefinition, elements).bind()

                ValidatedEnumValue(
                        value = expression.value,
                        enumDefinition = enumDefinition

                )
            }
            is Operation -> {
                when (findBasicOperation(expression.operationId).bind()) {
                    BasicOperation.TRANSFER -> {
                        validateArgumentsCount(expression.arguments, 2).bind()
                        val destination = validateArgument<Expression>(expression.arguments, 0).bind()
                        val count = validateArgument<Expression>(expression.arguments, 1).bind()
                        ValidatedTransferOperation(
                                destination = validateExpression(destination, elements).bind(),
                                count = validateExpression(count, elements).bind()
                        )
                    }
                    BasicOperation.BALANCE -> {
                        validateArgumentsCount(expression.arguments, 0).bind()
                        ValidatedBalanceOperation()
                    }
                    BasicOperation.EQUALS -> {
                        validateArgumentsCount(expression.arguments, 2).bind()
                        val firstOperand = validateArgument<Expression>(expression.arguments, 0).bind()
                        val secondOperand = validateArgument<Expression>(expression.arguments, 1).bind()
                        ValidatedEqualsOperation(
                                firstOperand = validateExpression(firstOperand, elements).bind(),
                                secondOperand = validateExpression(secondOperand, elements).bind()
                        )
                    }
                    BasicOperation.SENDER -> {
                        validateArgumentsCount(expression.arguments, 0).bind()
                        ValidatedSenderOperation()
                    }
                    BasicOperation.MULTIPLY -> {
                        validateArgumentsCount(expression.arguments, 2).bind()
                        val firstOperand = validateArgument<Expression>(expression.arguments, 0).bind()
                        val secondOperand = validateArgument<Expression>(expression.arguments, 1).bind()
                        ValidatedMultiplyOperation(
                                firstOperand = validateExpression(firstOperand, elements).bind(),
                                secondOperand = validateExpression(secondOperand, elements).bind()
                        )
                    }
                    BasicOperation.TRANSFER_VALUE -> {
                        validateArgumentsCount(expression.arguments, 0).bind()
                        ValidatedTransferValueOperation()
                    }
                }
            }
            is VariableRef -> {
                validateVariableRef(expression, elements).bind()
            }
            is FunctionCall -> {
                ValidatedFunctionCall(
                        function = findElement<Function>(expression.function.id, elements).bind(),
                        functionRef = expression.function,
                        arguments = expression.arguments.map { validateFunctionArgument(it, elements).bind() }
                )
            }
            is ConstantValue -> {
                ValidatedConstantValue(
                        value = validateMetaValue<MetaPrimitiveValue>(expression.value).bind(),
                        type = validateType(expression.type, elements).bind()
                )
            }
            else -> {
                error("${expression.javaClass} is not supported").bind()
            }
        }
    }

    private inline fun <reified T : MetaValue> validateMetaValue(metaValue: MetaValue): Result<T, List<ElementValidationError>> {
        if (metaValue is T) {
            return Ok(metaValue)
        } else {
            return error("Expected ${T::class.java} but was ${metaValue::class.java}")
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

    private fun validateValue(type: ValidatedType, metaValue: MetaValue, allElements: List<Element>): Result<ValidatedValue, List<ElementValidationError>> = binding {
        when (type) {
            is ValidatedBasicType -> {
                if (metaValue is MetaPrimitiveValue) {
                    validateBasicTypeValue(type, metaValue).bind()
                } else {
                    Err(listOf(ElementValidationError("Expected MetaPrimitiveValue as value of basic type"))).bind()
                }
            }
            is ValidatedEnumDefinitionRef -> {
                if (metaValue is MetaPrimitiveValue) {
                    Ok(ValidatedEnumValue(metaValue.value, type.enumDefinition)).bind()
                } else {
                    Err(listOf(ElementValidationError("Expected MetaPrimitiveValue as value of enum type"))).bind()
                }
            }
            is ValidatedDefinitionRef -> {
                if (metaValue is MetaMapValue) {
                    val baseDefinitions = mutableListOf<Definition>()
                    for (baseDefinitionRef in type.definition.baseDefinitions) {
                        val baseDefinition = findElement<Definition>(baseDefinitionRef.definition, allElements).bind()
                        baseDefinitions.add(baseDefinition)
                    }
                    // TODO: check base definitions of base definitions
                    val properties = type.definition.properties + baseDefinitions.flatMap { it.properties }
                    if (metaValue.values.keys != properties.map { it.name }.toSet()) {
                        Err(listOf(ElementValidationError("Expected keys ${properties.map { it.name }.toSet()} but was ${metaValue.values.keys}"))).bind()
                    } else {
                        val values = metaValue.values.mapValues { validateValue(validateType(properties.find { prop -> prop.name == it.key }!!.type, allElements).bind(), it.value, allElements).bind() }
                        ValidatedDefinitionValue(values, type.definition)
                    }
                } else {
                    Err(listOf(ElementValidationError("Expected MetaMapValue as value of definition type"))).bind()
                }
            }
            else -> {
                Err(listOf(ElementValidationError("Type ${type::class} is not supported with metaValue"))).bind()
            }
        }
    }

    private fun validateBasicTypeValue(validatedBasicType: ValidatedBasicType, metaValue: MetaPrimitiveValue): Result<ValidatedValue, List<ElementValidationError>> {
        return if (validatedBasicType.basicType == BasicType.UINT) {
            return if (metaValue.value.toIntOrNull() == null) {
                error("Value ${metaValue.value} cannot be converted to UINT")
            } else {
                Ok(ValidatedBasicTypeLiteralValue(metaValue, validatedBasicType))
            }
        } else {
            Ok(ValidatedBasicTypeLiteralValue(metaValue, validatedBasicType))
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

    private fun findBasicOperation(id: Id): Result<BasicOperation, List<ElementValidationError>> {
        val basicOperation = BasicOperation.values().find { it.id == id }
        if (basicOperation == null) {
            return error("Operation with $id not found")
        } else {
            return Ok(basicOperation)
        }
    }

    private inline fun <reified T : Element> findElement(id: Id, elements: List<Element>): Result<T, List<ElementValidationError>> {
        val element = elements
                .filterIsInstance<T>()
                .find { it.id == id }
                ?: return Err(listOf(ElementValidationError("Element with id $id and type ${T::class.java} not found")))

        return Ok(element)
    }

    private fun validateArgumentsCount(arguments: List<FunctionArgument>, expectedCount: Int): Result<Any?, List<ElementValidationError>> {
        if (arguments.size == expectedCount) {
            return Ok(null)
        } else {
            return error("Expected $expectedCount arguments but was ${arguments.size}")
        }
    }

    private fun validateFunctionArgument(argument: FunctionArgument, elements: List<Element>): Result<ValidatedFunctionArgument, List<ElementValidationError>> {
        if (argument is Expression) {
            return validateExpression(argument, elements)
        } else {
            return error("Expected ${Expression::class.java} but was ${argument::class.java}")
        }
    }

    private inline fun <reified T : FunctionArgument> validateArgument(arguments: List<FunctionArgument>, index: Int): Result<T, List<ElementValidationError>> {
        val argument = arguments[index]
        if (argument is T) {
            return Ok(argument)
        } else {
            return error("Argument $index was expected to be ${T::class.java}")
        }
    }

    private fun error(message: String): Result<Nothing, List<ElementValidationError>> {
        return Err(listOf(ElementValidationError(message)))
    }
}