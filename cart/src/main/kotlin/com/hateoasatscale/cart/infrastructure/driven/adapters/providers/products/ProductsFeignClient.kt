package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

// @FeignClient(value = "products-service", configuration = [OauthFeignConfig::class])
@FeignClient(value = "products-service")
fun interface ProductsFeignClient {

    @GetMapping("/products/{name}")
    fun findBy(@PathVariable name: String): ProviderProductDto
}
