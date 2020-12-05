package com.piotrwalkusz.smartlaw.compiler.common

class Output {

    companion object {
        var LOCAL_OUTPUT: ThreadLocal<Output> = ThreadLocal.withInitial { Output() }

        fun get(): Output {
            return LOCAL_OUTPUT.get()
        }
    }

    private val messages: MutableList<OutputMessage> = mutableListOf()
        get() = field.toMutableList()

    fun addError(message: String) {
        messages.add(OutputMessage(OutputMessageType.ERROR, message))
    }

    fun addWarning(message: String) {
        messages.add(OutputMessage(OutputMessageType.WARNING, message))
    }

    fun addMessage(type: OutputMessageType, message: String) {
        messages.add(OutputMessage(type, message))
    }
}
