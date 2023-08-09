package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.SymbolProcessor

interface SymbolProcessorFactory {

  fun createProcessor(): SymbolProcessor

}
