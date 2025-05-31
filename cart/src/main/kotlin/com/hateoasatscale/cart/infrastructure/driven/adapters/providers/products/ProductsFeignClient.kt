package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import com.hateoasatscale.cart.infrastructure.config.FeignConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "products-service", configuration = [FeignConfiguration::class])
fun interface ProductsFeignClient {

    @GetMapping("/api/v1/startup")
    fun startup(): ProviderProductsStartupDto
}
