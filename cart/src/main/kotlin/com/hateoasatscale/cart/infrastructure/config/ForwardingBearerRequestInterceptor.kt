package com.hateoasatscale.cart.infrastructure.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component

@Component
class ForwardingBearerRequestInterceptor : RequestInterceptor {
    override fun apply(restTemplate: RequestTemplate) {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth is JwtAuthenticationToken && !restTemplate.headers().containsKey(AUTHORIZATION)) {
            restTemplate.header(AUTHORIZATION, "Bearer %s".format(auth.token.tokenValue))
        }
        println(restTemplate.headers())
    }
}
