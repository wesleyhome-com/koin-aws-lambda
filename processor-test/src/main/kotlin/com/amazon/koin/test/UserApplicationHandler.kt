package com.amazon.koin.test

import com.amazon.koin.test.module.SecondModule
import com.amazon.koin.test.module.UserHandlerModule
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.wesleyhome.koin.aws.lambda.LambdaHandler

@LambdaHandler(moduleClasses = [UserHandlerModule::class, SecondModule::class])
interface UserApplicationHandler : RequestHandler<String, String>
