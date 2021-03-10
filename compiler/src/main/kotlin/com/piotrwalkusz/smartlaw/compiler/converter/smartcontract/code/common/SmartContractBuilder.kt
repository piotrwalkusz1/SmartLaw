package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common

import com.piotrwalkusz.smartlaw.core.model.element.Element

abstract class SmartContractBuilder {

    abstract var name: String?
    abstract var elements: List<Element>

    abstract fun build(): String
}