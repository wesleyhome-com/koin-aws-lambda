package com.wesleyhome.koin.aws.lambda.test.modules

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [RepositoryModule::class])
@ComponentScan("com.wesleyhome.koin.aws.lambda.test.services.faker")
class FakerGeneratorModule
