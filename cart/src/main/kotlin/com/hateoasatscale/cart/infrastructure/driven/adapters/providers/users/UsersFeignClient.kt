package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient("users-service")
fun interface UsersFeignClient {

    @GetMapping("\$application.network.services.users.endpoints.user-info")
    fun findBy(): ProviderUserDto
}
