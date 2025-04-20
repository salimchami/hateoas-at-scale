package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.domain.FindUser
import jakarta.validation.constraints.NotBlank
import org.springframework.beans.factory.annotation.Value
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersResource(
    private val findUser: FindUser,
    @Value("\${application.network.gateway-url}") private val gatewayUrl: String,
    @Value("\${application.network.services.products.name}") private val productsServiceName: String,
    @Value("\${application.network.services.products.endpoints.list}") private val productsEndpoint: String,
    @Value("\${application.network.services.carts.name}") private val cartsServiceName: String,
    @Value("\${application.network.services.carts.endpoints.list}") private val cartsEndpoint: String,

    ) {

    @GetMapping("/users/{username}")
    fun userInfo(@PathVariable @NotBlank username: String): EntityModel<UserDto> {
        val user = findUser.by(username)
        val content = UserDto(
            this.gatewayUrl,
            this.productsServiceName,
            this.productsEndpoint,
            this.cartsServiceName,
            this.cartsEndpoint,
            user.username,
            user.firstname,
            user.lastname
        )
        return EntityModel.of(content)
    }
}
