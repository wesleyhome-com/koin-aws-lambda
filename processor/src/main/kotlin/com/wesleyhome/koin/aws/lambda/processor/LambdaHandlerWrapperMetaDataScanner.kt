package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.wesleyhome.koin.aws.lambda.LambdaHandler

class LambdaHandlerWrapperMetaDataScanner(
  private val logger: KSPLogger
) {

  fun scanWrappers(resolver: Resolver): List<LambdaHandlerMetaData.Wrapper> {
    logger.warn("Scanning for Handlers")
    val handlerSymbols = resolver.getSymbolsWithAnnotation(LambdaHandler::class.qualifiedName!!).toList()
    return handlerSymbols.filterIsInstance<KSClassDeclaration>()
      .map { createWrapperClass(it) }
  }

  private fun createWrapperClass(annotated: KSAnnotated) : LambdaHandlerMetaData.Wrapper {
    val componentClass = annotated as KSClassDeclaration
    logger.warn("Component: $componentClass")
    val superTypes = componentClass.superTypes.map { it }.toList()
    logger.warn("Super Types: $superTypes")
    superTypes.firstOrNull { it.element.toString() == "RequestHandler" } ?: error("LambdaHandler must implement RequestHandler")
    val handlerAnnotation = componentClass.annotations.first { it.shortName.asString() == LambdaHandler::class.simpleName!! }
    val packageName = componentClass.getPackageName().filterForbiddenKeywords()
    val arguments = handlerAnnotation.arguments
    return LambdaHandlerMetaData.Wrapper(
      packageName = packageName,
      annotatedInterface = componentClass,
      arguments = arguments.associate {
        it.name?.asString()!! to it.value
      }
    )
  }
}

fun KSClassDeclaration.getPackageName() : String = packageName.asString()
val forbiddenKeywords = listOf("interface")
fun String.filterForbiddenKeywords() : String{
  return split(".").joinToString(".") {
    if (it in forbiddenKeywords) "`$it`" else it
  }
}
