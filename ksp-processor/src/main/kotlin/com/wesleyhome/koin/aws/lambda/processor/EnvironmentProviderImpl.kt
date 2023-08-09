package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import org.koin.core.annotation.Single

@Single
class EnvironmentProviderImpl : EnvironmentProvider {

  private lateinit var environment: SymbolProcessorEnvironment
  override val logger: KSPLogger by lazy { environment.logger }
  override val codeGenerator: CodeGenerator by lazy { environment.codeGenerator }
  override fun initialize(environment: SymbolProcessorEnvironment) {
    this.environment = environment
  }
}
