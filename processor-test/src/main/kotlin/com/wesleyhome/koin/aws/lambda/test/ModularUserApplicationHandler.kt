package com.wesleyhome.koin.aws.lambda.test

import com.wesleyhome.koin.aws.lambda.test.modules.ApplicationModule
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.wesleyhome.koin.aws.lambda.LambdaHandler

@LambdaHandler(
    name = "UserApplicationWrapper",
    packageName = "com.wesleyhome.koin.aws.lambda.test.application",
    moduleClasses = [ApplicationModule::class]
)
interface ModularUserApplicationHandler : RequestHandler<NameInput, User>
