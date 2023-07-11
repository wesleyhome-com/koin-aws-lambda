plugins {
  // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
  kotlin("jvm")

  // Apply the java-library plugin for API and implementation separation.
  `library-publishing-conventions`
}
group = "com.wesleyhome.koin"
version = "0.3.0-SNAPSHOT"
dependencies {
  api("io.insert-koin:koin-annotations:1.2.2")
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

tasks.javadoc {
  if (JavaVersion.current().isJava9Compatible) {
    (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
  }
}
