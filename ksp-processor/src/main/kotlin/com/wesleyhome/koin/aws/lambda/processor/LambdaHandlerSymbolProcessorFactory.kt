package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import org.koin.core.annotation.Single

@Single
class LambdaHandlerSymbolProcessorFactory(
  private val symbolProcessor: SymbolProcessor
) : SymbolProcessorFactory {
  override fun createProcessor(): SymbolProcessor {
    return symbolProcessor
  }
}
