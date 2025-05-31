package com.hateoasatscale.cart

import WebConfiguration
import com.hateoasatscale.cart.infrastructure.config.FeignClientConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Import

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableDiscoveryClient
@EnableFeignClients
@Import(FeignClientConfiguration::class, WebConfiguration::class)
class CartApplication

fun main(args: Array<String>) {
    runApplication<CartApplication>(*args)
}
