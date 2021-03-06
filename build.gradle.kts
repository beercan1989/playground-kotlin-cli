import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    application
    kotlin("jvm") version "1.4.0"
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    // Logging
    implementation("ch.qos.logback:logback-classic:1.2.3")

    // CLIKT - Command line helper
    implementation("com.github.ajalt.clikt:clikt:3.0.0-rc")

    // Slack
    implementation("com.github.Ullink:simple-slack-api:1.2.0")

    // Test framework
    testImplementation("io.kotest:kotest-runner-junit5:4.2.2")
    testImplementation("io.kotest:kotest-assertions-core:4.2.2")

    // Mocking
    testImplementation("io.mockk:mockk:1.10.0")
}

application {
    mainClassName = "uk.co.baconi.playground.cli.CliCommandKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = TestExceptionFormat.FULL
    }
}
