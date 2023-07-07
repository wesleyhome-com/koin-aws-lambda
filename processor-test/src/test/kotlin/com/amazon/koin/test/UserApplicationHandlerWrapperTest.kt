package com.amazon.koin.test

import com.amazonaws.services.lambda.runtime.Context
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.stopKoin

@ExtendWith(MockKExtension::class)
class UserApplicationHandlerWrapperTest {

  @InjectMockKs
  private lateinit var wrapper: UserApplicationHandlerWrapper

  @MockK
  private lateinit var context: Context

  @AfterEach
  fun tearDown() {
    stopKoin()
  }

  @Test
  fun handleRequest_justin() {
    val response = wrapper.handleRequest("justin", context)
    assertThat(response).isEqualTo("Hello `Justin Wesley`!!!")
  }

  @Test
  fun handleRequest_defaultUser() {
    val response = wrapper.handleRequest("justi", context)
    assertThat(response).isEqualTo("Hello `Default User`!!!")
  }
}
