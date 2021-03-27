package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common

import com.piotrwalkusz.smartlaw.compiler.validator.model.ValidatedElement

abstract class SmartContractBuilder {

    abstract var name: String?
    abstract var elements: List<ValidatedElement>

    abstract fun build(): String
}