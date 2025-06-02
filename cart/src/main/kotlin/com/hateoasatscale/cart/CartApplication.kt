package com.hateoasatscale.cart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ComponentScan(
    basePackages = ["com.hateoasatscale.cart"],
    excludeFilters = [
        ComponentScan.Filter(
            type = FilterType.REGEX,
            pattern = ["com.hateoasatscale.cart.infrastructure.config.specific.*"],
        ),
    ],
)
@EnableDiscoveryClient
@EnableFeignClients
class CartApplication

fun main(args: Array<String>) {
    runApplication<CartApplication>(*args)
}
