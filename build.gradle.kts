plugins {
  id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}
group = "com.wesleyhome.koin"
val versionString = providers.gradleProperty("version").get()
version = versionString

nexusPublishing {
  this.repositories {
    sonatype()
  }
}

task("release") {
  doLast {
    val version = versionString.removeSuffix("-SNAPSHOT")
    val versionParts = version.split(".")
    val lastVersion = versionParts.last()
    val nextVersion = providers.environmentVariable("next-version")
      .orElse(
        versionParts.subList(0, versionParts.size - 1)
          .plus((lastVersion.toInt() + 1).toString())
          .joinToString(separator = ".", postfix = "-SNAPSHOT")
      ).get()
    releaseVersion(version)
    releaseVersion(nextVersion)
  }
}
fun releaseVersion(version: String) {
  val propsFile = File("gradle.properties")
  propsFile.bufferedWriter().use {
    it.write("version=$version%n".format())
  }
  val isSnapshot = version.endsWith("-SNAPSHOT")
  val action = if (isSnapshot) {
    "Creating new snapshot"
  } else {
    "Releasing Version"
  }
  exec {
    commandLine("cmd", "/c", "echo", "git", "add", "version.properties")
  }
  exec {
    commandLine("cmd", "/c", "echo", "git", "commit", "-m", """"$action $version"""")
  }
  if(!isSnapshot) {
    exec {
      commandLine("cmd", "/c", "echo", "git", "tag", """"koin-aws-lambda-$version"""")
    }
  }
  exec {
    val commands = listOf("cmd", "/c", "echo", "git", "push", "origin").also {
      if(!isSnapshot) {
        it + "--tags"
      } else {
        it
      }
    }
    commandLine(commands)
  }
}
