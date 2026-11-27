package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSTypeReference
import com.wesleyhome.koin.aws.lambda.LambdaHandler
import org.koin.core.annotation.Single


@Single
class LambdaHandlerWrapperMetaDataScanner(
  environmentProvider: EnvironmentProvider
) : MetaDataScanner {
  private val logger by lazy { environmentProvider.logger }

  override fun scanWrappers(resolver: Resolver): List<LambdaHandlerMetaData.Wrapper> {
    logger.warn("Scanning for Handlers")
    val handlerSymbols = resolver.getSymbolsWithAnnotation(LambdaHandler::class.qualifiedName!!).toList()
    return handlerSymbols.filterIsInstance<KSClassDeclaration>()
      .map { createWrapperClass(it) }
  }

  private fun createWrapperClass(annotated: KSAnnotated): LambdaHandlerMetaData.Wrapper {
    val componentClass = annotated as KSClassDeclaration
    val superTypes = componentClass.superTypes.map { it }.toList()
    superTypes.firstOrNull { it.isRequestHandler() }
      ?: error("LambdaHandler must implement RequestHandler")
    val handlerAnnotation =
      componentClass.annotations.first { it.shortName.asString() == LambdaHandler::class.simpleName!! }
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

fun KSClassDeclaration.getPackageName(): String = packageName.asString()

private const val REQUEST_HANDLER_FQN = "com.amazonaws.services.lambda.runtime.RequestHandler"

/**
 * Matches by resolved qualified name. KSP2's `element.toString()` includes type arguments
 * (e.g. `RequestHandler<In, Out>`), so a simple-name string comparison no longer works.
 */
fun KSTypeReference.isRequestHandler(): Boolean =
  resolve().declaration.qualifiedName?.asString() == REQUEST_HANDLER_FQN
val forbiddenKeywords = listOf("interface")
fun String.filterForbiddenKeywords(): String {
  return split(".").joinToString(".") {
    if (it in forbiddenKeywords) "`$it`" else it
  }
}
