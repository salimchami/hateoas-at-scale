package com.hateoasatscale.products.infrastructure.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Configuration
class FeignClientConfiguration {
    @Bean
    fun feignRequestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template: RequestTemplate ->
            val requestAttributes = RequestContextHolder.getRequestAttributes()
            if (requestAttributes is ServletRequestAttributes) {
                val request = requestAttributes.request

                val forwardedHeaders = listOf(
                    "X-Forwarded-Host",
                    "X-Forwarded-Port",
                    "X-Forwarded-Proto",
                    "X-Forwarded-For",
                    "X-Forwarded-Prefix",
                )

                forwardedHeaders.forEach { headerName ->
                    val headerValue = request.getHeader(headerName)
                    if (!headerValue.isNullOrBlank()) {
                        template.header(headerName, headerValue)
                    }
                }

                val authorizationHeader = request.getHeader("Authorization")
                if (!authorizationHeader.isNullOrBlank()) {
                    template.header("Authorization", authorizationHeader)
                }
            }
            println("#######################################################")
            println("users feign client conf : ")
            println(template.headers())
        }
    }
}
