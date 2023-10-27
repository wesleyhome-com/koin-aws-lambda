package com.wesleyhome.koin.aws.lambda.test.modules

import com.wesleyhome.koin.aws.lambda.test.UserApplicationHandler
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module(includes = [RepositoryModule::class, FakerGeneratorModule::class])
@ComponentScan("com.amazon.example.kotlin.services.application")
class ApplicationModule
