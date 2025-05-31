package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users

import com.hateoasatscale.cart.infrastructure.config.FeignConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient("users-service", configuration = [FeignConfiguration::class])
fun interface UsersFeignClient {

    @GetMapping("/api/v1/users/user-info")
    fun findBy(): ProviderUserDto
}
