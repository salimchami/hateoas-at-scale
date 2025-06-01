package com.hateoasatscale.products.infrastructure.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

// @Component
class ForwardingRequestInterceptor : RequestInterceptor {
    override fun apply(requestTemplate: RequestTemplate) {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth is JwtAuthenticationToken && !requestTemplate.headers().containsKey(AUTHORIZATION)) {
            requestTemplate.header(AUTHORIZATION, "Bearer %s".format(auth.token.tokenValue))
        }
    }
}
