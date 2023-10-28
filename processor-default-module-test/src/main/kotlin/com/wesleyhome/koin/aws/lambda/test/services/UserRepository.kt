package com.wesleyhome.koin.aws.lambda.test.services

import com.wesleyhome.koin.aws.lambda.test.User

interface UserRepository {
  fun findByName(name: String): User?
  fun save(temp: User)
}
