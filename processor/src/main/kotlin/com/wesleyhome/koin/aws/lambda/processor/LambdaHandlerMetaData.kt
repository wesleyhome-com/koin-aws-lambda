package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.symbol.KSClassDeclaration

sealed class LambdaHandlerMetaData {
  data class Wrapper(
    val packageName: String,
    val annotatedInterface: KSClassDeclaration,
    val arguments: Map<String, Any?>
  )
}
