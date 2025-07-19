package com.example.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.kaiqkt.kt.tools.security.utils.TokenUtils
import io.azam.ulidj.ULID
import io.restassured.RestAssured
import io.restassured.config.ObjectMapperConfig
import io.restassured.mapper.ObjectMapperType
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTest {

    @LocalServerPort
    var port: Int = 0

    @Value("\${auth.access-token-secret}")
    lateinit var accessTokenSecret: String

    @Autowired
    lateinit var mapper: ObjectMapper


    @BeforeAll
    fun before() {
        RestAssured.config = RestAssured.config()
            .objectMapperConfig(
                ObjectMapperConfig(ObjectMapperType.JACKSON_2)
                    .jackson2ObjectMapperFactory { _, _ ->
                        mapper
                    }
            )
        RestAssured.baseURI = "http://localhost:$port"
    }


    fun mockAuthentication(): String {
        val accessToken = TokenUtils.generateJwt(
            mapOf(
                "user_id" to ULID.random(),
                "session_id" to ULID.random(),
                "roles" to listOf("ROLE_USER")
            ), 1, accessTokenSecret
        )

        return accessToken
    }
}
