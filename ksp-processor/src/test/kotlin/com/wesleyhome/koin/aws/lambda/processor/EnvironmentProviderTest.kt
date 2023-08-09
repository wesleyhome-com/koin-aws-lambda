package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class EnvironmentProviderTest {

  @InjectMockKs
  private lateinit var environmentProvider: EnvironmentProvider

  @MockK
  private lateinit var environment: SymbolProcessorEnvironment
  @MockK
  private lateinit var logger: KSPLogger
  @MockK
  private lateinit var codeGenerator: CodeGenerator

  @Test
  fun initialize() {
    every { environment.logger } returns logger
    every { environment.codeGenerator } returns codeGenerator
    environmentProvider.initialize(environment)
    assertThat(environmentProvider.logger).isEqualTo(logger)
    assertThat(environmentProvider.codeGenerator).isEqualTo(codeGenerator)
    verify { environment.logger }
    verify { environment.codeGenerator }
  }
}
