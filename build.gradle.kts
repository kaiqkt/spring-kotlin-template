plugins {
    // Core plugins
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"

    // Code quality
    jacoco

    // OpenAPI
     id("org.openapi.generator") version "7.9.0"
}

group = "com.example"
version = "0.1.0-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

// Spring Boot configuration
springBoot {
    buildInfo()
}

// JaCoCo configuration
jacoco {
	toolVersion = "0.8.12"
    reportsDirectory = layout.buildDirectory.dir("reports/jacoco")
}

repositories {
    mavenCentral()
    // Uncomment and configure if using GitHub Packages
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/kaiqkt/*")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GPR_USER")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GPR_API_KEY")
        }
    }

}

dependencies {
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Custom Libraries
    implementation("com.kaiqkt:kt-tools-healthcheck:1.0.0")
    implementation("com.kaiqkt:kt-tools-security:1.0.6")

    // Documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")

    // Utilities
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("io.azam.ulidj:ulidj:1.0.1")

    // Test Dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.mockk:mockk:1.13.14")
    testImplementation("com.auth0:java-jwt:4.4.0")
    testImplementation("org.mock-server:mockserver-netty:5.15.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:testcontainers:1.21.3")
    testImplementation("org.testcontainers:junit-jupiter:1.20.6")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Kotlin compiler configuration
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


// JaCoCo test report configuration
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.required = true
        html.outputLocation = layout.buildDirectory.dir("reports/jacoco/html")
    }

    // Exclude generated code, configuration classes, etc.
    afterEvaluate {
        classDirectories.setFrom(
            files(classDirectories.files.map {
                fileTree(it) {
                    exclude(
                        "**/config/**",
                        "**/dto/**",
                        "**/exception/**",
                        "**/model/**",
                        "**/*Application*"
                    )
                }
            })
        )
    }
}

// JaCoCo coverage verification
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "1.0".toBigDecimal()
                counter = "LINE"
            }
            limit {
                minimum = "1.0".toBigDecimal()
                counter = "BRANCH"
            }
        }
    }
}

//OpenAPI code generation
sourceSets {
    main {
        java {
            srcDir("${layout.buildDirectory.get()}/generated/src/main/kotlin")
        }
    }
}

tasks.compileKotlin {
    dependsOn(tasks.openApiGenerate)
}

openApiGenerate {
    generatorName = "kotlin-spring"
    inputSpec = "$rootDir/src/main/resources/static/api-docs.yml"
    outputDir = "${layout.buildDirectory.get()}/generated"
    apiPackage = "${project.group}.generated.api"
    modelPackage = "${project.group}.generated.model"
    configOptions = mapOf(
        "dateLibrary" to "java8",
        "interfaceOnly" to "true",
        "useBeanValidation" to "true",
        "useSpringBoot3" to "true"
    )
}
