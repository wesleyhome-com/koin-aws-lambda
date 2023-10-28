package com.wesleyhome.koin.aws.lambda.test.services.faker

import com.wesleyhome.koin.aws.lambda.test.User
import com.wesleyhome.koin.aws.lambda.test.services.UserGenerator
import net.datafaker.Faker
import org.koin.core.annotation.Single

@Single
class DataFakerGenerator : UserGenerator {
  private val faker = Faker()

  override fun generate(userName: String): User {
    return User(
      firstName = faker.name().firstName(),
      lastName = faker.name().lastName(),
      preferredName = faker.name().fullName(),
      userName = userName
    )
  }
}
