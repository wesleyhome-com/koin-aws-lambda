package com.wesleyhome.koin.aws.lambda.test.services.application

import com.wesleyhome.koin.aws.lambda.test.NameInput
import com.wesleyhome.koin.aws.lambda.test.User
import com.wesleyhome.koin.aws.lambda.test.ModularUserApplicationHandler
import com.wesleyhome.koin.aws.lambda.test.services.UserGenerator
import com.wesleyhome.koin.aws.lambda.test.services.UserRepository
import com.amazonaws.services.lambda.runtime.Context
import org.koin.core.annotation.Single

@Single
class ModularUserApplication(
  private val userRepository: UserRepository,
  private val userGenerator: UserGenerator
) : ModularUserApplicationHandler {
  override fun handleRequest(input: NameInput, context: Context): User {
    return userRepository.findByName(input.name).let {
      if (it == null) {
        val temp = userGenerator.generate(input.name)
        userRepository.save(temp)
        temp
      } else {
        it
      }
    }
  }
}
