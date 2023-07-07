package com.amazon.koin.test

interface UserRepository {
  fun findUser(name: String): User?
}
