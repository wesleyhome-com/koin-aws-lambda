plugins {
  kotlin("jvm")
  id("com.google.devtools.ksp") version "2.0.10-1.0.24"
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
  implementation(project(":${rootProject.name}-annotations"))
  implementation("io.insert-koin:koin-core:3.5.6")
  implementation("com.amazonaws:aws-lambda-java-events:3.13.0")
  implementation("com.amazonaws:aws-lambda-java-core:1.2.3")
  implementation("org.slf4j:slf4j-nop:2.0.14")

  // Data Faker
  implementation("io.github.dvgaba:easy-random-core:7.0.0")
  implementation("net.datafaker:datafaker:2.3.1")

  implementation(kotlin("reflect"))
  testImplementation(kotlin("test"))
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.3")
  testImplementation("io.mockk:mockk:1.13.12")
//  testImplementation("org.assertj:assertj-core:3.24.2")
  testImplementation("com.willowtreeapps.assertk:assertk:0.28.1")
  ksp(project(":${rootProject.name}-ksp-processor"))
  ksp("io.insert-koin:koin-ksp-compiler:1.3.1")
}

// Apply a specific Java toolchain to ease working on different environments.
kotlin {
  jvmToolchain(17)
}

tasks.test {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}
