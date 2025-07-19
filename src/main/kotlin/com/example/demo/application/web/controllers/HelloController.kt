package com.example.demo.application.web.controllers

import com.example.demo.generated.application.web.controllers.HelloApi
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController: HelloApi {

    override fun hello(): ResponseEntity<String> {
        return ResponseEntity(HttpStatus.OK)
    }
}
