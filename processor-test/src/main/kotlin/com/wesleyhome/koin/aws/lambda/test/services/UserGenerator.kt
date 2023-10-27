package com.wesleyhome.koin.aws.lambda.test.services

import com.wesleyhome.koin.aws.lambda.test.User

interface UserGenerator {
  fun generate(userName: String): User

}
