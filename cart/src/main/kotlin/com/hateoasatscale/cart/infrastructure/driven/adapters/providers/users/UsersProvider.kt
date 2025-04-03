package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users

import org.springframework.cloud.openfeign.FeignClient

@FeignClient("users")
fun interface UsersProvider {
    fun findUser(id: Long): ProviderUserDto
}