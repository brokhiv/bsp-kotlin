import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    antlr
    application
}

group = "me.ivobr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.0")
    antlr("org.antlr:antlr4:4.9.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    dependsOn(tasks.generateGrammarSource)
    kotlinOptions.jvmTarget = "1.8"
}

tasks.generateGrammarSource {
    outputDirectory = File("src/main/gen")
}

application {
    mainClass.set("MainKt")
}