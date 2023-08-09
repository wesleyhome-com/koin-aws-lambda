package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LambdaHandlerProcessorTest {

  @InjectMockKs
  private lateinit var processor: LambdaHandlerProcessor
  @MockK
  private lateinit var wrapperGenerator: LambdaHandlerWrapperGenerator
  @MockK
  private lateinit var scanner: MetaDataScanner
  @MockK
  private lateinit var environmentProvider: EnvironmentProvider
  @MockK
  private lateinit var resolver: Resolver
  @MockK
  private lateinit var ksClassDeclaration: KSClassDeclaration
  @MockK
  private lateinit var logger: KSPLogger

  @Test
  fun process() {
    val wrapperList = listOf(
      LambdaHandlerMetaData.Wrapper(
        packageName = "com.example",
        annotatedInterface = ksClassDeclaration,
        arguments = emptyMap()
      )
    )
    every { scanner.scanWrappers(resolver) } returns wrapperList
    every { environmentProvider.logger } returns logger
    every { logger.warn("Processing classes") } just Runs
    every { wrapperGenerator.generateWrappers(wrapperList) } just Runs
    val list = processor.process(resolver)
    assertThat(list).isEmpty()
    verify { logger.warn("Processing classes") }
    verify { scanner.scanWrappers(resolver) }
    verify { wrapperGenerator.generateWrappers(wrapperList) }
  }
}
