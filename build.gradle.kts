plugins {
  id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}
group = "com.wesleyhome.koin"
version = providers.gradleProperty("version").get()

nexusPublishing {
  this.repositories {
    sonatype()
  }
}
