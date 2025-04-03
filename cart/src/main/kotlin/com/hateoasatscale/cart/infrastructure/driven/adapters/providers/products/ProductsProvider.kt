package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.cloud.openfeign.FeignClient

@FeignClient("products")
fun interface ProductsProvider {
    fun findBy(ids: List<Long>): List<ProviderProductDto>
}