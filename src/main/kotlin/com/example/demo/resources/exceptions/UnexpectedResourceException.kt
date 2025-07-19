package com.example.demo.resources.exceptions

import org.slf4j.LoggerFactory
import kotlin.collections.firstOrNull
import kotlin.jvm.java

class UnexpectedResourceException(message: String) : Exception(message) {
    init {
        LoggerFactory.getLogger(UnexpectedResourceException::class.java).error(getLoggedMessage())
    }

    fun sourceLocation(): String? {
        val stackElement = this.stackTrace.firstOrNull() ?: return null

        return "${stackElement.fileName}:${stackElement.lineNumber}"
    }

    fun getLoggedMessage(): String {
        return "Unexpected resource exception occurred at (${sourceLocation()}): $message"
    }
}
