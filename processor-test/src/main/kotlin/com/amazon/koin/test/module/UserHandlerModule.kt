package com.amazon.koin.test.module

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("com.amazon.koin.test.module.one")
class UserHandlerModule

