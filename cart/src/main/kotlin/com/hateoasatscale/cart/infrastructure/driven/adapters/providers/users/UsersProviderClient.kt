package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Component
class UsersProviderClient(val restTemplate: RestTemplate) : UsersProvider {
    override fun findBy(id: Long): ProviderUserDto {
        return this.restTemplate.getForObject<ProviderUserDto>("/users/$id")
    }
}