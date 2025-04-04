package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Component
class UsersProviderClient(val restTemplate: RestTemplate, @Autowired val consulDiscoveryClient: ConsulDiscoveryClient) :
    UsersProvider {
    override fun findBy(id: Long): ProviderUserDto {
        val usersService =
            consulDiscoveryClient.getInstances("users").firstOrNull() ?: throw Exception("No users service available")
        val usersServiceUrl = usersService.uri.toString()
        return this.restTemplate.getForObject<ProviderUserDto>("$usersServiceUrl/users/$id")
    }
}