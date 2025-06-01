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
            it.header("Authorization", generateToken())
            it.header("Host", "localhost")
            it.header("X-Forwarded-For", "localhost")
            it.header("X-Forwarded-Host", "localhost")
            it.header("X-Forwarded-Prefix", "/carts-service")
            it.header("X-Forwarded-Port", "4200")
            it.header("X-Forwarded-Proto", "http")
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
