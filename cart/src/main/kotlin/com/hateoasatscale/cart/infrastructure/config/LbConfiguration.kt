package com.hateoasatscale.cart.infrastructure.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class LbConfiguration {

    @LoadBalanced
    @Bean
    fun loadBalancedRestTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.interceptors.add(HeaderForwardingInterceptor())
        return restTemplate
    }
}
