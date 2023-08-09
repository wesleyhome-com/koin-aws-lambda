package com.wesleyhome.koin.aws.lambda.processor

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.toTypeParameterResolver
import com.squareup.kotlinpoet.ksp.writeTo
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent

@Single
class KSPLambdaHandlerWrapperGenerator(
  environmentProvider: EnvironmentProvider
) : LambdaHandlerWrapperGenerator {

  private val codeGenerator by lazy { environmentProvider.codeGenerator }
  private val logger by lazy { environmentProvider.logger }


  override fun generateWrappers(lambdaWrappers: List<LambdaHandlerMetaData.Wrapper>) {
    logger.warn("Generate ${lambdaWrappers.size} Wrappers")
    lambdaWrappers.forEach { generateWrapper(it) }
  }

  private fun generateWrapper(lambdaWrapper: LambdaHandlerMetaData.Wrapper) {
    logger.warn("Generate ${lambdaWrapper.annotatedInterface} Wrapper")
    val fileName = lambdaWrapper.annotatedInterface.toString() + "Wrapper"
    generateWrapper(lambdaWrapper, fileName)
  }

  private fun generateWrapper(
    lambdaWrapper: LambdaHandlerMetaData.Wrapper,
    fileName: String
  ) {
    val annotatedInterface = lambdaWrapper.annotatedInterface
    val typeParameterResolver = annotatedInterface.typeParameters.toTypeParameterResolver()
    val requestHandler =
      annotatedInterface.superTypes.filter { it.element.toString() == RequestHandler::class.simpleName }.first().let {
        it.toTypeName(typeParameterResolver) as ParameterizedTypeName
      }
    val moduleClasses = lambdaWrapper.arguments["moduleClasses"] as List<KSType>
    val injectName = MemberName(KoinComponent::class.java.packageName, "inject")
    val startKoin = MemberName("org.koin.core.context", "startKoin")
    val initializerCodeBlock = if (moduleClasses.size == 1) {
      CodeBlock.of(
        """
        %M {
            modules(%T().module)
        }
        
      """.trimIndent(),
        startKoin,
        moduleClasses[0].toClassName()
      )
    } else {
      val moduleTypes = moduleClasses.joinToString { "%T().module" }
      CodeBlock.of(
        """
        %M {
            modules($moduleTypes)
        }
        
      """.trimIndent(),
        startKoin,
        *moduleClasses.map { it.toClassName() }.toTypedArray()
      )
    }
    logger.warn("RequestHandler: ${requestHandler::class}")
    logger.warn("RequestHandlerParameters: ${requestHandler.typeArguments}")
    val (inputParam, outputParam) = requestHandler.typeArguments
    val delegateProperty = PropertySpec.builder("delegate", annotatedInterface.toClassName())
      .addModifiers(KModifier.PRIVATE)
      .delegate("%M()", injectName)
      .build()
    val fileSpec = FileSpec.builder(lambdaWrapper.packageName, fileName)
      .addImport("org.koin.ksp.generated", "module")
      .addType(
        TypeSpec.classBuilder(fileName)
          .addSuperinterface(KoinComponent::class)
          .addSuperinterface(requestHandler)
          .addProperty(delegateProperty)
          .addInitializerBlock(initializerCodeBlock)
          .addFunction(
            FunSpec.builder("handleRequest")
              .addModifiers(KModifier.OVERRIDE)
              .addParameter("input", inputParam)
              .addParameter("context", Context::class.java)
              .returns(outputParam)
              .addStatement("return %N.handleRequest(input, context)", delegateProperty)
              .build()
          )
          .build()
      )
      .build()
    fileSpec.writeTo(codeGenerator, false)
  }
}
