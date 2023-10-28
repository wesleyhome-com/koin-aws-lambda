package com.wesleyhome.koin.aws.lambda.test

import com.amazonaws.services.lambda.runtime.RequestHandler
import com.wesleyhome.koin.aws.lambda.LambdaHandler

@LambdaHandler
interface UserApplicationHandler : RequestHandler<NameInput, User>
