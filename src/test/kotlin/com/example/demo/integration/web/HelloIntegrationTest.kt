package com.example.demo.integration.web

import com.example.demo.integration.IntegrationTest
import io.restassured.RestAssured.given
import kotlin.test.Test

class HelloIntegrationTest : IntegrationTest() {

    @Test
    fun `test hello`() {
        given()
            .get("/hello")
            .then()
            .statusCode(200)
    }
}
