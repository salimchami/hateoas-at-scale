package com.hateoasatscale.products

import com.hateoasatscale.products.infrastructure.config.FeignConfiguration
import com.hateoasatscale.products.infrastructure.config.ForwardedHeadersRequestInterceptor
import com.hateoasatscale.products.infrastructure.config.HateoasConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Import

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@Import(ForwardedHeadersRequestInterceptor::class, HateoasConfiguration::class, FeignConfiguration::class)
@EnableDiscoveryClient
@EnableFeignClients
class ProductsApplication

fun main(args: Array<String>) {
    runApplication<ProductsApplication>(*args)
}
