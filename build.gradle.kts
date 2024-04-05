plugins {
  id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
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
    exec {
      commandLine("cmd", "/c", "git", "push", "origin", "--tags")
    }

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
    commandLine("cmd", "/c", "git", "add", "gradle.properties")
  }
  exec {
    commandLine("cmd", "/c", "git", "commit", "-m", """"$action $version"""")
  }
  if(!isSnapshot) {
    exec {
      commandLine("cmd", "/c", "git", "tag", """"koin-aws-lambda-$version"""")
    }
  }
}
