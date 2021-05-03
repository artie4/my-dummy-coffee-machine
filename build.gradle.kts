import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.0"
    application
}

group = "me.artie4"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

object Versions {
    const val kotlin = "1.4.31"
    const val jvm = "1.8"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")
    testImplementation(kotlin("test-junit5"))
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = Versions.jvm
}

application {
    mainClassName = "CoffeeMachineAppKt"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "machine.${application.mainClassName}"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
