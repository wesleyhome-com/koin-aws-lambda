plugins {
  id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}
group = "com.wesleyhome.koin"
version = "0.2.0"

nexusPublishing {
  this.repositories {
    sonatype()
  }
}
