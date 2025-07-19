package com.example.demo.unit.application.web.security

import com.kaiqkt.kt.tools.security.dtos.Authentication
import com.kaiqkt.kt.tools.security.utils.TokenUtils
import kotlin.to

object ContextSampler {

    fun sample(data: Map<String, Any>? = null): Authentication {
        val defaultData = data ?: mapOf(
            "session_id" to "sessionId",
            "user_id" to "userId",
            "roles" to listOf("ROLE_USER")
        )
        val token = TokenUtils.generateJwt(
            data ?: mapOf("session_id" to "sessionId", "user_id" to "userId", "roles" to listOf("ROLE_USER")),
            15, "secret"
        )

        return Authentication(token, defaultData)
    }
}
