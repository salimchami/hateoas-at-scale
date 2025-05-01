package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.domain.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class UserMapping(
    @Value("\${application.network.gateway-url}") private val gatewayUrl: String,
    @Value("\${application.network.services.products.name}") private val productsServiceName: String,
    @Value("\${application.network.services.products.endpoints.list}") private val productsEndpoint: String,
    @Value("\${application.network.services.carts.name}") private val cartsServiceName: String,
    @Value("\${application.network.services.carts.endpoints.list}") private val cartsEndpoint: String,
) {
    fun domainToDto(user: User): UserDto {
        return UserDto(
            gatewayUrl,
            productsServiceName,
            productsEndpoint,
            cartsServiceName,
            cartsEndpoint,
            user.permissions,
            user.username,
            user.firstname,
            user.lastname
        )
    }

    fun domainToDto(users: List<User>): List<UserDto> {
        return users.map { this.domainToDto(it) }
    }
}