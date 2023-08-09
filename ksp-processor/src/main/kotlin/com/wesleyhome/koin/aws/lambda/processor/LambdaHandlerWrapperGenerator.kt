package com.wesleyhome.koin.aws.lambda.processor

interface LambdaHandlerWrapperGenerator {

  fun generateWrappers(lambdaWrappers: List<LambdaHandlerMetaData.Wrapper>)
}
