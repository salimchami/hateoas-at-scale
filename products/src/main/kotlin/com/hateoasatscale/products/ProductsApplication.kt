package com.hateoasatscale.products

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ComponentScan(
    basePackages = ["com.hateoasatscale.products"],
    excludeFilters = [
        ComponentScan.Filter(
            type = FilterType.REGEX,
            pattern = ["com.hateoasatscale.products.infrastructure.config.specific.*"],
        ),
    ],
)
@EnableDiscoveryClient
@EnableFeignClients
class ProductsApplication

fun main(args: Array<String>) {
    runApplication<ProductsApplication>(*args)
}
