package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import com.hateoasatscale.cart.infrastructure.config.OauthFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(value = "products-service", configuration = [OauthFeignConfig::class])
fun interface ProductsFeignClient {

    @GetMapping("/products/{name}")
    fun findBy(@PathVariable name: String): ProviderProductDto
}
