plugins {
  kotlin("jvm")
  id("com.google.devtools.ksp") version "2.1.20-1.0.31"
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
  implementation("io.insert-koin:koin-core:4.0.2")
  implementation("com.amazonaws:aws-lambda-java-events:3.15.0")
  implementation("com.amazonaws:aws-lambda-java-core:1.2.3")
  implementation("org.slf4j:slf4j-nop:2.0.17")

  // Data Faker
  implementation("io.github.dvgaba:easy-random-core:7.1.1")
  implementation("net.datafaker:datafaker:2.4.2")

  implementation(kotlin("reflect"))
  testImplementation(kotlin("test"))
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.1")
  testImplementation("io.mockk:mockk:1.13.17")
//  testImplementation("org.assertj:assertj-core:3.24.2")
  testImplementation("com.willowtreeapps.assertk:assertk:0.28.1")
  ksp(project(":${rootProject.name}-ksp-processor"))
  ksp("io.insert-koin:koin-ksp-compiler:2.0.0")
}

// Apply a specific Java toolchain to ease working on different environments.
kotlin {
  jvmToolchain(17)
}

tasks.test {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}
