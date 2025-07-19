package com.example.demo

import com.kaiqkt.kt.tools.healthcheck.HealthCheckConfig
import com.kaiqkt.kt.tools.security.configs.SecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
@Import(SecurityConfig::class, HealthCheckConfig::class)
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
