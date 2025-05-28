package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users

import feign.HeaderMap
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient("users-service")
fun interface UsersFeignClient {

    @GetMapping("/api/v1/users/user-info")
    fun findBy(@HeaderMap headers: Map<String, Any>): ProviderUserDto
}
