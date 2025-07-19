package com.example.domain.exceptions

import org.springframework.http.HttpStatus

enum class ErrorType(val message: String, val code: Int){
    INVALID_ARGUMENTS("Invalid arguments", HttpStatus.BAD_REQUEST.value()),
}
