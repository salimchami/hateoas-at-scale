package com.hateoasatscale.products.infrastructure.config

import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfiguration {

    @Bean
    fun forwardedHeadersRequestInterceptor(): RequestInterceptor {
        return ForwardedHeadersRequestInterceptor()
    }
}
