package com.example.demo.domain.exceptions


class DomainException(val type: ErrorType) : Exception(type.message)
