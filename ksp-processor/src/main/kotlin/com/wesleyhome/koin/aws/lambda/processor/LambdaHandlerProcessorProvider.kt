package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class LambdaHandlerProcessorProvider : SymbolProcessorProvider {


  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
    val koinApplication = startKoin {
      modules(LambdaHandlerModule().module)
    }
    val koin = koinApplication.koin
    val processorFactory = koin.get<SymbolProcessorFactory>()
    val environmentProvider = koin.get<EnvironmentProvider>()
    environmentProvider.initialize(environment)
    return processorFactory.createProcessor().also { koinApplication.close() }
  }
}
