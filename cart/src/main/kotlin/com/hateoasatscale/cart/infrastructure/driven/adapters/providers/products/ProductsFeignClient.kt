package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(value = "products-service")
fun interface ProductsFeignClient {

    @GetMapping("/api/v1/products/{name}")
    fun findBy(@PathVariable name: String): ProviderProductDto
}
