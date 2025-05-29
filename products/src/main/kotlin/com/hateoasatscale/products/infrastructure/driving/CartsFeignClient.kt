package com.hateoasatscale.products.infrastructure.driving

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.Link
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "carts-service")
fun interface CartsFeignClient {

    @GetMapping("/api/v1/startup")
    fun startupLinks(): List<Link>
}
