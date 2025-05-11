package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("users-service")
fun interface UsersFeignClient {

    @GetMapping("/api/v1/users/{username}")
    fun findBy(@PathVariable username: String): ProviderUserDto
}
