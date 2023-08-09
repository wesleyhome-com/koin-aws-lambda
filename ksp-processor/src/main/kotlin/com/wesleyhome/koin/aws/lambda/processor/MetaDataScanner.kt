package com.wesleyhome.koin.aws.lambda.processor

import com.google.devtools.ksp.processing.Resolver

interface MetaDataScanner {

  fun scanWrappers(resolver: Resolver): List<LambdaHandlerMetaData.Wrapper>
}
