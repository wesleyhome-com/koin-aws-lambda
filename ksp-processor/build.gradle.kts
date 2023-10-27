plugins {
  // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
  kotlin("jvm")
  id("com.google.devtools.ksp") version "1.8.20-1.0.11"
  // Apply the java-library plugin for API and implementation separation.
  `library-publishing-conventions`
}
group = "com.wesleyhome.koin"
//version = providers.gradleProperty("version").get()

dependencies {
  api(project(":${rootProject.name}-annotations"))
  implementation("com.google.devtools.ksp:symbol-processing-api:1.8.0-1.0.9")
  implementation("com.squareup:kotlinpoet:1.14.2")
  implementation("com.squareup:kotlinpoet-ksp:1.14.2")
  implementation("io.insert-koin:koin-core:3.4.2")
  implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
  testImplementation(kotlin("test"))
  implementation("org.junit.jupiter:junit-jupiter:5.10.0")
  testImplementation("org.assertj:assertj-core:3.24.2")
  testImplementation("io.mockk:mockk:1.13.5")
  ksp("io.insert-koin:koin-ksp-compiler:1.2.2")
}

// Apply a specific Java toolchain to ease working on different environments.
kotlin {
  jvmToolchain(17)
}

tasks.test {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}
