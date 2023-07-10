/*
 * This file was generated by the Gradle 'init' task.
 *
 * The settings file is used to specify which projects to include in your build.
 *
 * Detailed information about configuring a multi-project build in Gradle can be found
 * in the user manual at https://docs.gradle.org/8.1.1/userguide/multi_project_builds.html
 */
pluginManagement {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
  }
}

plugins {
  kotlin("jvm") version "1.8.20" apply false
}


buildscript {
  dependencies {
    classpath(kotlin("gradle-plugin", version = "1.8.0"))
  }
}

rootProject.name = "koin-aws-lambda"
include("annotations", "processor", "processor-test")
