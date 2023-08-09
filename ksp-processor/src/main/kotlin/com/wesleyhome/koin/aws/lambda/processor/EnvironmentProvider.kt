package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment

interface EnvironmentProvider {
  val codeGenerator: CodeGenerator
  val logger: KSPLogger
  fun initialize(environment: SymbolProcessorEnvironment)
}
