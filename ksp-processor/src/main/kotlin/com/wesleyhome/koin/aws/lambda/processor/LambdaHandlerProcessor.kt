package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import org.koin.core.annotation.Single

@Single
class LambdaHandlerProcessor(
  private val wrapperGenerator: LambdaHandlerWrapperGenerator,
  private val scanner: MetaDataScanner,
  private val environmentProvider: EnvironmentProvider
) : SymbolProcessor {


  override fun process(resolver: Resolver): List<KSAnnotated> {
    environmentProvider.logger.warn("Processing classes")
    val scanWrappers = scanner.scanWrappers(resolver)
    wrapperGenerator.generateWrappers(scanWrappers)
    return emptyList()
  }
}

