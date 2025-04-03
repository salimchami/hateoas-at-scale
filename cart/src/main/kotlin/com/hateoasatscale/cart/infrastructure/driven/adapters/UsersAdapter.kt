package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.User
import com.hateoasatscale.cart.domain.spi.UsersRepository
import org.springframework.stereotype.Component

@Component
class UsersAdapter : UsersRepository {
    override fun findBy(id: Long): User {
        return User("", "")
    }
}