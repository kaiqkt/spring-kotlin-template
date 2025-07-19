package com.example.integration.web

import com.example.integration.IntegrationTest
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
