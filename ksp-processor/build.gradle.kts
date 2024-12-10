plugins {
  // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
  kotlin("jvm")
  id("com.google.devtools.ksp") version "2.1.0-1.0.29"
  // Apply the java-library plugin for API and implementation separation.
  `library-publishing-conventions`
}
group = "com.wesleyhome.koin"
//version = providers.gradleProperty("version").get()

dependencies {
  api(project(":${rootProject.name}-annotations"))
  implementation("com.google.devtools.ksp:symbol-processing-api:2.1.0-1.0.29")
  implementation("com.squareup:kotlinpoet:2.0.0")
  implementation("com.squareup:kotlinpoet-ksp:2.0.0")
  implementation("io.insert-koin:koin-core:3.5.6")
  implementation("com.amazonaws:aws-lambda-java-core:1.2.3")
  testImplementation(kotlin("test"))
  implementation("org.junit.jupiter:junit-jupiter:5.11.3")
  testImplementation("org.assertj:assertj-core:3.26.3")
  testImplementation("io.mockk:mockk:1.13.13")
  ksp("io.insert-koin:koin-ksp-compiler:1.4.0")
}

// Apply a specific Java toolchain to ease working on different environments.
kotlin {
  jvmToolchain(17)
}

tasks.test {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}
