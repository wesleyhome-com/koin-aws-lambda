plugins {
  // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
  kotlin("jvm")
  // Apply the java-library plugin for API and implementation separation.
  `library-publishing-conventions`
}
group = "com.wesleyhome.aws.koin"
version = "0.2.0-SNAPSHOT"

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
