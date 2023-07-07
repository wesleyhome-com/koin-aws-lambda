package com.amazon.koin.test.module.one

import com.amazon.koin.test.UserApplicationHandler
import com.amazon.koin.test.UserService
import com.amazonaws.services.lambda.runtime.Context
import org.koin.core.annotation.Single

@Single
class UserApplication(private val userService: UserService) : UserApplicationHandler {
  override fun handleRequest(input: String, context: Context): String {
    val user = userService.getDefaultUser(input)
    val message = "Hello `${user.name}`!!!"
    println(message)
    return message
  }

}
