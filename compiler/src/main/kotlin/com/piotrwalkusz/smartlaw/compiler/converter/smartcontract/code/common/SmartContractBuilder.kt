package com.piotrwalkusz.smartlaw.compiler.converter.smartcontract.code.common

abstract class SmartContractBuilder {

    abstract var name: String?

    abstract fun build(): String
}