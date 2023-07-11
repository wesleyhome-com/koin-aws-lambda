package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated

class LambdaHandlerProcessor(
  codeGenerator: CodeGenerator,
  private val logger: KSPLogger
) : SymbolProcessor {

  private val generator = LambdaHandlerWrapperGenerator(codeGenerator, logger)
  private val scanner = LambdaHandlerWrapperMetaDataScanner(logger)

  override fun process(resolver: Resolver): List<KSAnnotated> {
    logger.warn("Processing classes")
    val scanWrappers = scanner.scanWrappers(resolver)
    generator.generateWrappers(scanWrappers)
    return emptyList()
  }
}

