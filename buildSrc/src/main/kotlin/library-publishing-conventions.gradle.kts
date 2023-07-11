plugins {
  `java-library`
  `maven-publish`
  signing
}

repositories {
  // Use Maven Central for resolving dependencies.
  mavenLocal()
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  withJavadocJar()
  withSourcesJar()
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])
      pom {
        description = "Koin AWS Lambda Wrapper Generator"
        name = "Lambda Wrapper Generator for Koin applications"
        url = "https://github.com/justin-wesley/koin-aws-lambda"
        inceptionYear = "2023"
        scm {
          connection = "scm:git:https://github.com/justin-wesley/koin-aws-lambda.git"
          developerConnection = "scm:git:https://github.com/justin-wesley/koin-aws-lambda.git"
          url = "https://github.com/justin-wesley/koin-aws-lambda.git"
          tag = "HEAD"
        }
        developers {
          developer {
            id = "justin"
            name = "Justin Wesley"
            roles = listOf("Software Development Engineer")
          }
        }
        licenses {
          license {
            name = "The Apache Software License, Version 2.0"
            url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
          }
        }
      }
    }
  }
}

signing {
  val isCI = System.getenv("CI") == "true"
  setRequired { !project.version.toString().endsWith("-SNAPSHOT") && !project.hasProperty("skipSigning") }
  if(isCI) {
    val signingKey: String? by project
    useInMemoryPgpKeys(signingKey, null)
  }
  sign(publishing.publications["mavenJava"])
}
//
//release {
//  buildTasks = listOf("build", "publishToSonatype", "closeAndReleaseSonatypeStagingRepository")
//  with(git) {
//    requireBranch = "master"
//  }
//}

tasks.javadoc {
  if (JavaVersion.current().isJava9Compatible) {
    (options as StandardJavadocDocletOptions).apply {
      addBooleanOption("html5", true)
    }
  }
}

fun isOnCIServer() = System.getenv("CI") == "true"
