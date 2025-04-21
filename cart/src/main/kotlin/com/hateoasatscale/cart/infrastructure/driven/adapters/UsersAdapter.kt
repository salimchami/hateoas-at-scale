package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.User
import com.hateoasatscale.cart.domain.spi.UsersRepository
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.UsersFeignClient
import org.springframework.stereotype.Component

@Component
class UsersAdapter(private val usersFeignClient: UsersFeignClient) : UsersRepository {
    override fun findBy(username: String): User {
        val user = usersFeignClient.findBy(username)
        return User(user.username)
    }
}