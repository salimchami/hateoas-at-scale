package com.hateoasatscale.users.infrastructure.driving

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.Link
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(value = "products-service")
fun interface ProductsFeignClient {

    @GetMapping("/api/v1/startup")
    fun startupLinks(@RequestHeader headers: Map<String, String>
): List<Link>
}
