/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/8.1.1/userguide/building_java_projects.html
 */

plugins {
  // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
  kotlin("jvm") version "1.8.22"
  // Apply the java-library plugin for API and implementation separation.
  `java-library`
}

repositories {
  // Use Maven Central for resolving dependencies.
  mavenCentral()
}

dependencies {
  api(project(":annotations"))
  implementation("com.google.devtools.ksp:symbol-processing-api:1.8.0-1.0.9")
  implementation("com.squareup:kotlinpoet:1.14.2")
  implementation("com.squareup:kotlinpoet-ksp:1.14.2")
  implementation("io.insert-koin:koin-core:3.4.2")
  implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
  testImplementation(kotlin("test"))
}

// Apply a specific Java toolchain to ease working on different environments.
kotlin {
  jvmToolchain(11)
}

tasks.test {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}
