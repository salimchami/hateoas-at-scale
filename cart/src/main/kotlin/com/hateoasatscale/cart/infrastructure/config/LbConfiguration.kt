package com.hateoasatscale.cart.infrastructure.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class LbConfiguration {

    @LoadBalanced
    @Bean
    fun loadBalancedRestTemplate(): RestTemplate {
        val restTemplate = RestTemplateBuilder().build()
        restTemplate.interceptors.add(HeaderForwardingInterceptor())
        return restTemplate
    }
}
