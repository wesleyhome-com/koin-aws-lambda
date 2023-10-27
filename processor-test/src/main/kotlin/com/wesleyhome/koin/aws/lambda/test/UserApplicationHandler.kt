package com.wesleyhome.koin.aws.lambda.test

import com.wesleyhome.koin.aws.lambda.test.modules.ApplicationModule
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.wesleyhome.koin.aws.lambda.LambdaHandler

@LambdaHandler(moduleClasses = [ApplicationModule::class])
interface UserApplicationHandler : RequestHandler<NameInput, User>
