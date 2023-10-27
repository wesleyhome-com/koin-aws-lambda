package com.wesleyhome.koin.aws.lambda.test.services.repository

import com.wesleyhome.koin.aws.lambda.test.User
import com.wesleyhome.koin.aws.lambda.test.services.UserRepository
import org.koin.core.annotation.Single

@Single
class InMemoryRepository : UserRepository {
  private val users = mutableMapOf<String, User>()
  override fun findByName(name: String): User? {
    return users[name]
  }

  override fun save(temp: User) {
    users[temp.userName] = temp
  }
}
