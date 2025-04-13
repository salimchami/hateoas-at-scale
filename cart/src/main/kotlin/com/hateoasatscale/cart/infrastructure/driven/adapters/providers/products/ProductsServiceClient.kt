package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("products-service")
fun interface ProductsServiceClient {

    @GetMapping("/products/{id}")
    fun findBy(@PathVariable id: Long): ProviderProductDto
}