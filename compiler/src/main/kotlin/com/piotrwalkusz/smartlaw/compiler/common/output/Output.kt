package com.piotrwalkusz.smartlaw.compiler.common.output

class Output {

    companion object {
        var LOCAL_OUTPUT: ThreadLocal<Output> = ThreadLocal.withInitial { Output() }

        fun get(): Output {
            return LOCAL_OUTPUT.get()
        }
    }

    private val messages: MutableList<OutputMessage> = mutableListOf()

    fun addError(message: String) {
        messages.add(OutputMessage(OutputMessageType.ERROR, message))
    }

    fun addWarning(message: String) {
        messages.add(OutputMessage(OutputMessageType.WARNING, message))
    }

    fun addMessage(type: OutputMessageType, message: String) {
        messages.add(OutputMessage(type, message))
    }

    fun getMessages(): List<OutputMessage> {
        return messages.toList()
    }
}
