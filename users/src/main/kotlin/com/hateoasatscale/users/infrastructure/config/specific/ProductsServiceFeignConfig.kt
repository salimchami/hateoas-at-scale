package com.hateoasatscale.users.infrastructure.config.specific

import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


@Configuration(proxyBeanMethods = false)
class ProductsServiceFeignConfig {

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        val interceptor = RequestInterceptor {
            val requestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
            val request = requestAttributes.request
            request.headerNames.toList().forEach { key -> println("$key: ${request.getHeader(key)}") }
            it.header("Authorization", generateToken())
            it.header("Host", request.getHeader("Host"))
            it.header("X-Forwarded-For", request.getHeader("X-Forwarded-For"))
            it.header("X-Forwarded-Host", request.getHeader("X-Forwarded-Host"))
            it.header("X-Forwarded-Prefix", request.getHeader("X-Forwarded-Prefix"))
            it.header("X-Forwarded-Port", request.getHeader("X-Forwarded-Port"))
            it.header("X-Forwarded-Proto", request.getHeader("X-Forwarded-Proto"))
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
