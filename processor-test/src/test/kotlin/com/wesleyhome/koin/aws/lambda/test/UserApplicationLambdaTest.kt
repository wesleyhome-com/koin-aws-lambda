package com.wesleyhome.koin.aws.lambda.test

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.amazonaws.services.lambda.runtime.Context
import com.wesleyhome.koin.aws.lambda.test.application.UserApplicationWrapper
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserApplicationLambdaTest {

  private val app = UserApplicationWrapper()

  @MockK
  private lateinit var context: Context

  @Test
  fun handleRequest() {
    val john = app.handleRequest(NameInput("John"), context)
    assertThat(john).isNotNull()
    assertThat(john.userName).isEqualTo("John")
    assertThat(app.handleRequest(NameInput("John"), context)).isEqualTo(john)
  }
}
