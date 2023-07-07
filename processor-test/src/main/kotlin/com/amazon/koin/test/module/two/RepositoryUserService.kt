package com.amazon.koin.test.module.two

import com.amazon.koin.test.User
import com.amazon.koin.test.UserRepository
import com.amazon.koin.test.UserService
import org.koin.core.annotation.Single

@Single
class RepositoryUserService(private val userRepository: UserRepository) : UserService {

  override fun getDefaultUser(id: String): User = userRepository.findUser(id) ?: User("Default User")
}
