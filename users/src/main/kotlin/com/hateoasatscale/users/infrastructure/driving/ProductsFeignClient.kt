package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.infrastructure.config.FeignConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.Link
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "products-service", configuration = [FeignConfiguration::class])
fun interface ProductsFeignClient {

    @GetMapping("/api/v1/startup")
    fun startupLinks(): List<Link>
}
