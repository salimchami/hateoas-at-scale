package com.hateoasatscale.products

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@AutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
class ProductsApplication


fun main(args: Array<String>) {
    runApplication<ProductsApplication>(*args)
}
