package com.amazon.koin.test

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.prop
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.*

@ExtendWith(MockKExtension::class)
class UserApplicationHandlerWrapperTest {

  @MockK
  private lateinit var context: Context

  private lateinit var wrapper: RequestHandler<String, String>

  private lateinit var wrapperClass: KClass<RequestHandler<String, String>>

  @BeforeEach
  fun setUp() {
    wrapperClass = (Class.forName(this::class.java.packageName + ".UserApplicationHandlerWrapper")
      as Class<RequestHandler<String, String>>)
      .kotlin
    wrapper = wrapperClass.createInstance()
  }

  @AfterEach
  fun tearDown() {
    stopKoin()
  }

  @OptIn(KoinInternalApi::class)
  @Test
  fun testWrapperClass() {
    val memberProperties = wrapperClass.memberProperties
    assertThat(memberProperties).hasSize(1)
    assertThat(memberProperties.first().returnType).isEqualTo(UserApplicationHandler::class.createType())
    val koinApplication = GlobalContext.getKoinApplicationOrNull()
    assertThat(koinApplication).isNotNull()
    val instanceSize = koinApplication!!.koin.instanceRegistry.size()
    assertThat(instanceSize).isEqualTo(6)
    val memberFunctions = wrapperClass.memberFunctions
    assertThat(memberFunctions).hasSize(5)
    val handleRequestFunction = memberFunctions.first()
    assertThat(handleRequestFunction)
    assertThat(handleRequestFunction).prop(KFunction<*>::name).isEqualTo("handleRequest")
    handleRequestFunction.parameters.also {
      assertThat(it[1].type).isEqualTo(String::class.createType())
      assertThat(it[2].type).isEqualTo(Context::class.createType())
    }
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

