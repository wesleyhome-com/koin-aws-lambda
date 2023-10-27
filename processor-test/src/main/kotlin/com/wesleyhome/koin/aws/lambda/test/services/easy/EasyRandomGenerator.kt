package com.wesleyhome.koin.aws.lambda.test.services.easy

import com.wesleyhome.koin.aws.lambda.test.User
import com.wesleyhome.koin.aws.lambda.test.services.UserGenerator
import org.jeasy.random.EasyRandom
import org.koin.core.annotation.Single

@Single
class EasyRandomGenerator : UserGenerator {
  private val generator = EasyRandom()
  override fun generate(userName: String): User {
    return generator.nextObject(User::class.java).copy(userName = userName)
  }
}
