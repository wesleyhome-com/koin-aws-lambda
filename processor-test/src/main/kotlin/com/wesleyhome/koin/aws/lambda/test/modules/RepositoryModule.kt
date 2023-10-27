package com.wesleyhome.koin.aws.lambda.test.modules

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("com.wesleyhome.koin.aws.lambda.test.services.repository")
class RepositoryModule
