package com.example.application.web.controllers

import com.example.generated.api.HelloApi
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController: HelloApi {

    override fun hello(): ResponseEntity<String> {
        return ResponseEntity(HttpStatus.OK)
    }
}
