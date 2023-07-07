package com.amazon.koin.test.module.one

import com.amazon.koin.test.User
import com.amazon.koin.test.UserRepository
import org.koin.core.annotation.Single

@Single
class ArrayUserRepositoryImpl: UserRepository {
  private val users = mapOf(
    "justin" to User("Justin Wesley")
  )

  override fun findUser(name: String): User? = users[name]
}
