import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
    application
}
group = "me.artie4"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test-junit5"))
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClassName = "machine.CoffeeMachineApp"
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = application.mainClassName
    }
    archiveName = "${rootProject.name}-${version}.jar"
}
