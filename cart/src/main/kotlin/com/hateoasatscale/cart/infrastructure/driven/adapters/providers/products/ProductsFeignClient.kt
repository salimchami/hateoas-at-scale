package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "products-service")
fun interface ProductsFeignClient {

    @GetMapping("/api/v1/startup")
    fun startup(): ProviderProductsStartupDto
}
