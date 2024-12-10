package com.wesleyhome.koin.aws.lambda

import kotlin.reflect.KClass

/**
 * Annotation to mark a class as a Lambda Handler.
 */
@Target(AnnotationTarget.CLASS)
annotation class LambdaHandler(
    /**
     * Array of KClass objects representing the modules to be included in the Lambda handler.
     * If not provided, an empty array is used.
     */
  val moduleClasses: Array<KClass<*>> = [],
    /**
     * String representing the name of the Lambda handler.
     * If not provided, an empty string is used.
     */
  val name: String = "",
    /**
     * String representing the package name of the Lambda handler.
     * If not provided, an empty string is used.
     */
  val packageName: String = ""
)
