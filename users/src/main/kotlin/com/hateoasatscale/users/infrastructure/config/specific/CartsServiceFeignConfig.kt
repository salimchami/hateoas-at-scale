package com.hateoasatscale.users.infrastructure.config.specific

import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

@Configuration(proxyBeanMethods = false)
class CartsServiceFeignConfig {

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        val interceptor = RequestInterceptor {
            val currentHeaders = it.headers()
            it.header("Authorization", generateToken())
            it.header("Host", currentHeaders["Host"])
            it.header("X-Forwarded-For", currentHeaders["X-Forwarded-For"])
            it.header("X-Forwarded-Host", currentHeaders["X-Forwarded-Host"])
            it.header("X-Forwarded-Prefix", currentHeaders["X-Forwarded-Prefix"])
            it.header("X-Forwarded-Port", currentHeaders["X-Forwarded-Port"])
            it.header("X-Forwarded-Proto", currentHeaders["X-Forwarded-Proto"])
        }
        return interceptor
    }


    private fun generateToken(): String? {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth is JwtAuthenticationToken) {
            return "Bearer %s".format(auth.token.tokenValue)
        }
        return null
    }
}
