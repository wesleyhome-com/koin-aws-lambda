plugins {
  kotlin("jvm")
  id("com.google.devtools.ksp") version "1.8.20-1.0.11"
  application
}

repositories {
  // Use Maven Central for resolving dependencies.
  mavenCentral()
}

sourceSets {
  main {
    java.srcDirs("build/generated/ksp/main/kotlin")
  }
}

dependencies {
  implementation(project(":annotations"))
  implementation("io.insert-koin:koin-core:3.4.2")
  implementation("com.amazonaws:aws-lambda-java-events:3.11.2")
  implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
  implementation("org.slf4j:slf4j-nop:2.0.7")
  testImplementation(kotlin("test"))
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
  testImplementation("io.mockk:mockk:1.13.5")
  testImplementation("org.assertj:assertj-core:3.24.2")
  ksp(project(":processor"))
  ksp("io.insert-koin:koin-ksp-compiler:1.2.2")
}

// Apply a specific Java toolchain to ease working on different environments.
kotlin {
  jvmToolchain(11)
}

tasks.test {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}
