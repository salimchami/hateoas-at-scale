package com.hateoasatscale.products.infrastructure.driving

import com.hateoasatscale.products.infrastructure.config.FeignConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.Link
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "carts-service", configuration = [FeignConfiguration::class])
fun interface CartsFeignClient {

    @GetMapping("/api/v1/startup")
    fun startupLinks(): List<Link>
}
