package com.hateoasatscale.users

import org.springframework.security.test.context.support.WithSecurityContext

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = WithJwtMockSecurityContextFactory::class)
annotation class WithJwtMock(val user: UserMock)

